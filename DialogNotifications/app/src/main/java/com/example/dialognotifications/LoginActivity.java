package com.example.dialognotifications;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends  AppCompatActivity {
    private TextInputEditText edtUsername, edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
    }

    private void login() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if(!username.isBlank() && !password.isBlank()) {
                redirect(username);
            }else {
                Toast.makeText(LoginActivity.this, "Login failed. Username or Password is empty.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void redirect(String username) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("name", username);
        startActivity(intent);
    }
}
