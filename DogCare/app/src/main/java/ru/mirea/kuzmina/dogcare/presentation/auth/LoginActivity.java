package ru.mirea.kuzmina.dogcare.presentation.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.kuzmina.data.local.AppDatabase;
import ru.mirea.kuzmina.data.repository.AuthRepositoryImpl;
import ru.mirea.kuzmina.data.storage.UserPreferences;
import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.domain.usecases.SignInUseCase;
import ru.mirea.kuzmina.domain.usecases.SignUpUseCase;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private Button loginButton;
    private Button registerButton;

    private AuthRepositoryImpl authRepository;
    private SignInUseCase signInUseCase;
    private SignUpUseCase signUpUseCase;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupDependencies();
        checkCurrentUser();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void setupDependencies() {
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            userPreferences = new UserPreferences(this);
            AppDatabase appDatabase = AppDatabase.getInstance(this);

            authRepository = new AuthRepositoryImpl(firebaseAuth, userPreferences, appDatabase);
            signInUseCase = new SignInUseCase(authRepository);
            signUpUseCase = new SignUpUseCase(authRepository);
        } catch (Exception e) {
            Toast.makeText(this, "Error initializing dependencies: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void checkCurrentUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                    if (currentUser != null) {
                        String userName = currentUser.getDisplayName();
                        if (userName == null || userName.isEmpty()) {
                            userName = userPreferences.getUserName();
                        }
                        if (userName == null || userName.isEmpty()) {
                            userName = extractNameFromEmail(currentUser.getEmail());
                        }

                        User user = new User(currentUser.getUid(), userName, currentUser.getEmail());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                goToMainActivity(user);
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private String extractNameFromEmail(String email) {
        if (email != null && email.contains("@")) {
            String namePart = email.split("@")[0];
            return namePart.substring(0, 1).toUpperCase() + namePart.substring(1);
        }
        return "Пользователь";
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите email и пароль", Toast.LENGTH_SHORT).show();
            return;
        }
        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    User user = signInUseCase.execute(email, password);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Вход выполнен успешно!", Toast.LENGTH_SHORT).show();
                            goToMainActivity(user);
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Ошибка входа: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(true);
                            registerButton.setEnabled(true);
                        }
                    });
                }
            }
        }).start();
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Пароль должен содержать минимум 6 символов", Toast.LENGTH_SHORT).show();
            return;
        }

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    User user = signUpUseCase.execute(email, password, name);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Регистрация выполнена успешно!", Toast.LENGTH_SHORT).show();
                            goToMainActivity(user);
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Ошибка регистрации: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(true);
                            registerButton.setEnabled(true);
                        }
                    });
                }
            }
        }).start();
    }
    private void goToMainActivity(User user) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("user_id", user.getId());
        intent.putExtra("user_name", user.getName());
        intent.putExtra("user_email", user.getEmail());
        startActivity(intent);
        finish();
    }
}