package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Logo_combustivel extends AppCompatActivity {

    private  final Timer timer = new Timer();
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_combustivel);

        getSupportActionBar().hide();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iniciaApp();
                    }
                });
            }
        };

        timer.schedule(timerTask,3000);
    }

    private void iniciaApp() {
        Intent intent = new Intent(getApplicationContext(),LitrosPorQuilomentros.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}