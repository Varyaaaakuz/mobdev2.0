package ru.mirea.kuzmina.data.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Tasks;
import ru.mirea.kuzmina.data.local.AppDatabase;
import ru.mirea.kuzmina.data.local.UserEntity;
import ru.mirea.kuzmina.data.storage.UserPreferences;
import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {
    private final FirebaseAuth firebaseAuth;
    private final UserPreferences userPreferences;
    private final AppDatabase appDatabase;

    public AuthRepositoryImpl(FirebaseAuth firebaseAuth, UserPreferences userPreferences, AppDatabase appDatabase) {
        this.firebaseAuth = firebaseAuth;
        this.userPreferences = userPreferences;
        this.appDatabase = appDatabase;
    }

    @Override
    public User signIn(String email, String password) throws Exception {
        try {
            Task<AuthResult> authResultTask = firebaseAuth.signInWithEmailAndPassword(email, password);

            AuthResult authResult = Tasks.await(authResultTask);
            FirebaseUser firebaseUser = authResult.getUser();

            if (firebaseUser == null) {
                throw new Exception("Sign in failed");
            }

            // Получаем имя пользователя
            String userName = firebaseUser.getDisplayName();
            if (userName == null || userName.isEmpty()) {
                userName = extractNameFromEmail(email);
            }

            // сохраняем в SharedPreferences
            userPreferences.saveUserEmail(email);
            userPreferences.saveUserName(userName);

            // сохраняем в Room
            User user = new User(firebaseUser.getUid(), userName, email);
            saveUserToRoom(user);

            return user;
        } catch (Exception e) {
            throw new Exception("Sign in failed: " + e.getMessage());
        }
    }

    @Override
    public User signUp(String email, String password, String name) throws Exception {
        try {
            Task<AuthResult> authResultTask = firebaseAuth.createUserWithEmailAndPassword(email, password);
            // ждем завершения задачи
            AuthResult authResult = Tasks.await(authResultTask);
            FirebaseUser firebaseUser = authResult.getUser();
            if (firebaseUser == null) {
                throw new Exception("Sign up failed");
            }

            // сохраняем в SharedPreferences
            userPreferences.saveUserEmail(email);
            userPreferences.saveUserName(name);

            // сохраняем в Room
            User user = new User(firebaseUser.getUid(), name, email);
            saveUserToRoom(user);

            return user;
        } catch (Exception e) {
            throw new Exception("Sign up failed: " + e.getMessage());
        }
    }

    @Override
    public void signOut() throws Exception {
        try {
            firebaseAuth.signOut();
            userPreferences.clearUserData();
            appDatabase.userDao().clearUsers();
        } catch (Exception e) {
            throw new Exception("Sign out failed: " + e.getMessage());
        }
    }

    @Override
    public User getCurrentUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            String userName = firebaseUser.getDisplayName();
            if (userName == null || userName.isEmpty()) {
                userName = userPreferences.getUserName();
                if (userName == null || userName.isEmpty()) {
                    userName = extractNameFromEmail(firebaseUser.getEmail());
                }
            }
            return new User(firebaseUser.getUid(), userName, firebaseUser.getEmail());
        }
        return null;
    }

    private String extractNameFromEmail(String email) {
        if (email != null && email.contains("@")) {
            String namePart = email.split("@")[0];
            return namePart.substring(0, 1).toUpperCase() + namePart.substring(1);
        }
        return "Пользователь";
    }

    private void saveUserToRoom(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
        appDatabase.userDao().insertUser(userEntity);
    }
}