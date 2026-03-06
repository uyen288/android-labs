package com.example.lifecycledemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnExplicit;
    Button btnImplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "Hàm onCreate được gọi");

        event();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "Hàm onStart được gọi");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "Hàm onResume được gọi");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "Hàm onPause được gọi");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "Hàm onStop được gọi");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "Hàm onDestroy được gọi");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle", "Hàm onRestart được gọi");
    }

    private void event() {
        btnExplicit = findViewById(R.id.btn_explicit);
        btnImplicit = findViewById(R.id.btn_implicit);

        btnExplicit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        });

        btnImplicit.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://google.com"));
            startActivity(intent);
        });
    }

}