package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    TextView tvIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this,Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ejecuta toda la pantalla
                startActivity(i);
            }
        }, 3000);
        tvIniciar = findViewById(R.id.tvIniciar);
    }
}