package com.example.lab10_simplecounter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    Button btnCounter;

    ExecutorService executorService;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);
        btnCounter = findViewById(R.id.btn_count);

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        btnCounter.setOnClickListener(v -> startCounting());
    }

    private void startCounting() {
        executorService.execute(() -> {
            for(int i=1; i<=10; i++) {
                int count = i;
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(() -> {
                    tvDisplay.setText(String.valueOf(count));
                });
            }

            handler.post(() -> {
                Toast.makeText(MainActivity.this, "Da dem xong", Toast.LENGTH_SHORT).show();
            });
        });
    }
}