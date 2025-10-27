package com.example.loginantoniodelgadoportero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private final String VALID_USERNAME = "admin";
    private final String VALID_PASSWORD = "1234";

    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText etUsername, etPassword;
    private MaterialButton btnLogin;
    private TextView tvRegister, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        tilUsername = findViewById(R.id.til_username);
        tilPassword = findViewById(R.id.til_password);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        // 1. Cargar la animación que creamos en res/anim/fade_in.xml
        // Primero, importa las clases necesarias al inicio de tu fichero:
        // import android.view.animation.Animation;
        // import android.view.animation.AnimationUtils;

        Animation animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // 2. Identificar las vistas que queremos animar (ya deberías tenerlas)
        ImageView loginLogo = findViewById(R.id.login_logo);
        TextView loginTitle = findViewById(R.id.login_title);
        // (tilUsername y tilPassword ya están declaradas arriba como variables de clase)

        // 3. Aplicar la animación a cada vista
        loginLogo.startAnimation(animFadeIn);

        // 4. (Opcional, pero MUY recomendado) Crear un efecto escalonado
        //    Reutilizamos la misma animación pero le añadimos un retraso (StartOffset)
        //    para que los elementos no aparezcan todos a la vez.

        // Retraso de 200ms para el título
        Animation animFadeInDelay200 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeInDelay200.setStartOffset(200); // 200 milisegundos de retraso
        loginTitle.startAnimation(animFadeInDelay200);

        // Retraso de 400ms para los campos de texto y el botón
        Animation animFadeInDelay400 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeInDelay400.setStartOffset(400); // 400 milisegundos de retraso

        tilUsername.startAnimation(animFadeInDelay400);
        tilPassword.startAnimation(animFadeInDelay400);
        btnLogin.startAnimation(animFadeInDelay400);
        tvRegister.startAnimation(animFadeInDelay400);
        tvForgotPassword.startAnimation(animFadeInDelay400);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndLogin();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, R.string.toast_register_wip, Toast.LENGTH_SHORT).show();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,R.string.toast_forgot_wip, Toast.LENGTH_SHORT).show();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void validateAndLogin(){
        tilUsername.setError(null);
        tilPassword.setError(null);

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        boolean hasError =false;

        if(username.isEmpty()){
            tilUsername.setError(getString(R.string.error_field_required));
            hasError = true;
        }

        if (password.isEmpty()){
            tilPassword.setError(getString(R.string.error_field_required));
            hasError = true;
        }

        if (hasError){
            return;
        }

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)){
            Toast.makeText(this, R.string.toast_login_success, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);

            intent.putExtra("USERNAME_EXTRA", username);

            startActivity(intent);
        }else {
            tilPassword.setError(getString(R.string.error_invalid_credentials));
        }
    }

}