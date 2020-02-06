package com.epam.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button buttonFirst;
    private Handler handler = new Handler();

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSecond = findViewById(R.id.button_second);
        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = (int)(Math.random()*100)+1;
                Toast toast = Toast.makeText(getApplicationContext(),"- " + number + " -",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,400);
                toast.show();
            }
        });
    }

    public void startProcess(View view) {
        progressBar = findViewById(R.id.progress_bar);
        buttonFirst = findViewById(R.id.button_first);
        progressBar.setVisibility(View.VISIBLE);
        buttonFirst.setEnabled(false);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        buttonFirst.setEnabled(true);
                    }
                });
            }
        });
        thread.start();
    }
}