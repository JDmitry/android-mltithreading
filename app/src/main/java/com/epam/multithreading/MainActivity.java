package com.epam.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button buttonFirst;
    private Button buttonSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSecond = findViewById(R.id.button_second);
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
        progressBar.setVisibility(View.VISIBLE);

        buttonFirst = findViewById(R.id.button_first);
        buttonFirst.setEnabled(false);

        new HardProcess().execute();
    }

    public class HardProcess extends AsyncTask<Void,Void,Void> {
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            buttonFirst.setEnabled(true);
        }
    }
}