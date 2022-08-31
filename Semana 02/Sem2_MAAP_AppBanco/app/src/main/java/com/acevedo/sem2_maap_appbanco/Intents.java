package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intents extends AppCompatActivity implements View.OnClickListener {
    Button btnIntent1;
    Button btnIntent2;
    Button btnIntent3;
    Button btnIntent4;
    Button btnIntent5;
    EditText edtCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
        btnIntent1 = findViewById(R.id.btnIntent1);
        btnIntent2 = findViewById(R.id.btnIntent2);
        btnIntent3 = findViewById(R.id.btnIntent3);
        btnIntent4 = findViewById(R.id.btnIntent4);
        btnIntent5 = findViewById(R.id.btnIntent5);
        edtCompartir = findViewById(R.id.edtCompartir);

        btnIntent1.setOnClickListener(this);
        btnIntent2.setOnClickListener(this);
        btnIntent3.setOnClickListener(this);
        btnIntent4.setOnClickListener(this);
        btnIntent5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIntent1:{
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contactos/"));
                startActivity(i);
                break;
            }
            case R.id.btnIntent2:{
                Intent i = new Intent(Intent.ACTION_VOICE_COMMAND);
                startActivity(i);
                break;
            }
            case R.id.btnIntent3:{
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                startActivity(i);
                break;
            }
            case R.id.btnIntent4:{
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:+51982126861"));
                startActivity(i);
                break;
            }
            case R.id.btnIntent5:{
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,edtCompartir.getText().toString());
                Intent eleccion = Intent.createChooser(i,"Que aplicación desea utilizar: ");
                startActivity(eleccion);
                break;
            }
        }
    }
}