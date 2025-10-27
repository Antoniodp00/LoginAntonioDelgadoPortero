package com.example.loginantoniodelgadoportero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvWelcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcomeMessage = findViewById(R.id.tv_welcome_message);

        Intent intent = getIntent();

        String username = intent.getStringExtra("USERNAME_EXTRA");

        String welcomeText = getString(R.string.welcome_message, username);

        tvWelcomeMessage.setText(welcomeText);
    }
}