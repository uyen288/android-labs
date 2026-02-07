package com.example.switchscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tvName = findViewById(R.id.tv_name);
        tvName.setText("Xin chào, "+name);
    }
}
