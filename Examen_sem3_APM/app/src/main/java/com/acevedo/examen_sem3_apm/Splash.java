package com.acevedo.examen_sem3_apm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    TextView tvIniciar,tvRegistrar;
    public static final String ARCHIVO_PREF = "Preferencias_APM";
    String sUsuario, sPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvIniciar = findViewById(R.id.tvIniciar);
        tvRegistrar = findViewById(R.id.tvRegistrar);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //Si ya tiene registrada una cuenta debe de redirigir automaticamente al Main activity de lo contrario a Login

                SharedPreferences preferences = getSharedPreferences(ARCHIVO_PREF,0); // 0 = Lectura y escritura

                // precausion para no llarmar
                if(preferences.contains("user")){
                    sUsuario=preferences.getString("user","noUser");
                    sPassword=preferences.getString("password","noPassword");
//                    Toast.makeText(Splash.this, "Los valores registrados son: \n " +
//                            sUsuario +" "+ sPassword, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Splash.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }else{
                    //Toast.makeText(Splash.this, "Requiere iniciar sesion", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Splash.this,Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        },3000);



    }
}