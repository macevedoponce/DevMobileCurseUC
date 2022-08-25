package com.acevedo.maap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtSaludo;
    Button btnSaludo;
    String []texto = new String[5];
    ConstraintLayout colLayout;
    int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSaludo = findViewById(R.id.txtSaludo);
        btnSaludo = findViewById(R.id.btnSaludo);
        colLayout = findViewById(R.id.colLayaout);

        texto[0] = "Protected";
        texto[1] = "Override";
        texto[2] = "OnCreate";
        texto[3] = "Toast";
        texto[4] = "MakeText";

        btnSaludo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        cont ++;
        txtSaludo.setText(generarTexto());

        if(cont == 1){
            colLayout.setBackgroundResource(R.color.On);
        }

        else{
            colLayout.setBackgroundResource(R.color.Off);
            cont = 0;
        }

        //Toast.makeText(this,"contador: "+cont,Toast.LENGTH_SHORT).show();
    }



    private String generarTexto(){
        //int pos = (int) Math.round(Math.random()*texto.length)-1;
        Random aleatorio = new Random(System.currentTimeMillis());
        int pos = (int) aleatorio.nextInt(texto.length);

        // cambiar el color del main cuando se haga click en el boton


        return texto[pos];
    }
}