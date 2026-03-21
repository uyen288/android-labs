package com.example.lab14_mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab14_mvp.constract.ILoginConstract;
import com.example.lab14_mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginConstract.IView {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private ILoginConstract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initGUI();
    }

    private void initGUI() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress_bar);

        mPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            mPresenter.login(email, password);
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        btnLogin.setEnabled(true);
    }

    @Override
    public void loginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}