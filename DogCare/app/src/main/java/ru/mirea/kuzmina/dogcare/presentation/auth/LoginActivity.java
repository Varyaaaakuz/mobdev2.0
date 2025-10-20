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
    private TextView registerTitle;
    private TextView loginLink;

    private SignInUseCase signInUseCase;
    private SignUpUseCase signUpUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupDependencies();

        // Проверяем если пользователь уже авторизован
        // checkCurrentUser();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        registerTitle = findViewById(R.id.registerTitle);
        loginLink = findViewById(R.id.loginLink);
        showRegistrationForm();

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerTitle.getText().toString().equals("Создайте новый аккаунт")) {
                    showLoginForm();
                } else {
                    showRegistrationForm();
                }
            }
        });

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

    private void showRegistrationForm() {
        registerTitle.setText("Создайте новый аккаунт");
        nameEditText.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        loginLink.setText("Уже есть аккаунт? Войдите");
    }

    private void showLoginForm() {
        registerTitle.setText("Войдите в свой аккаунт");
        nameEditText.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        loginLink.setText("Нет аккаунта? Зарегистрируйтесь");
    }

    private void setupDependencies() {
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            UserPreferences userPreferences = new UserPreferences(this);
            AppDatabase appDatabase = AppDatabase.getInstance(this);

            AuthRepositoryImpl authRepository = new AuthRepositoryImpl(firebaseAuth, userPreferences, appDatabase);
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
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    if (auth.getCurrentUser() != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                goToMainActivity();
                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        }).start();
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
                            goToMainActivity();
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

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите email и пароль", Toast.LENGTH_SHORT).show();
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
                    User user = signUpUseCase.execute(email, password);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Регистрация выполнена успешно!", Toast.LENGTH_SHORT).show();
                            goToMainActivity();
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

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}