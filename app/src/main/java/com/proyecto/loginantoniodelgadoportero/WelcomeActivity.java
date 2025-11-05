package com.proyecto.loginantoniodelgadoportero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTextView = findViewById(R.id.welcomeTextView);

        // Recibir el nombre de usuario de LoginActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME")) {
            String username = intent.getStringExtra("USERNAME");
            String welcomeMessage = getString(R.string.welcome_message) + " " + username;
            welcomeTextView.setText(welcomeMessage);
        }
    }
}
