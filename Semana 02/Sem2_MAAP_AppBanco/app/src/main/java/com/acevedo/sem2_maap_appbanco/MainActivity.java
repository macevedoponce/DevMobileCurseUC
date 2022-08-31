package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSplash;
    Button btnLogin;
    Button btnContactos;
    Button btnRegistrar;
    Button btnReporte;
    Button btnGoogle;
    Button btnIntents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // asociando cada objeto java con su respectivo objeto xml
        btnSplash = findViewById(R.id.btnSplash);
        btnLogin = findViewById(R.id.btnLogin);
        btnContactos = findViewById(R.id.btnContactos);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnReporte = findViewById(R.id.btnReporte);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnIntents = findViewById(R.id.btnIntents);

        //activando detección de click en botones
        btnSplash.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnContactos.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        btnReporte.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnIntents.setOnClickListener(this);

        //Recepcionando datos enviados desde Login
        String sNombreRecibido = getIntent().getStringExtra("nombreUsuario");
        String sPassRecibido = getIntent().getStringExtra("passUsuario");
        String sEmailRecibido = getIntent().getStringExtra("emailUsuario");
        //mostrando los datos recibidos en un Toast
        Toast.makeText(this, "Usuario: "+ sNombreRecibido + "Pass: " + sPassRecibido + "Email: " + sEmailRecibido, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:{
                Intent i = new Intent(this,Login.class);
                startActivity(i);
                break;
            }
            case R.id.btnSplash:{
                Intent i = new Intent(this,Splash.class);
                startActivity(i);
                break;
            }
            case R.id.btnRegistrar:{
                Intent i = new Intent(this,Registrar.class);
                startActivity(i);
                break;
            }
            case R.id.btnReporte:{
                Intent i = new Intent(this,Reporte.class);
                startActivity(i);
                break;
            }
            case R.id.btnContactos:{
                Intent i = new Intent(this,Contactos.class);
                startActivity(i);
                break;
            }
            case R.id.btnGoogle:{
                // Intent implicito -> solo mostrar la uri en el aplicativo que corresponda
                // pueden ser de varios tipos como abrir camara, tomar foto,. mostrar imagen, etc ... investigar más
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(i);
                break;
            }
            case R.id.btnIntents:{
                Intent i = new Intent(this, Intents.class);
                startActivity(i);
                break;
            }
        }

    }
}