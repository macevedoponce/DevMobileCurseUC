package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class texto_a_voz extends AppCompatActivity {
    EditText edttextoInput;
    Button btnConvertiraTexto;
    ConversorSpeech conversor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_avoz);
        edttextoInput = findViewById(R.id.edttextoInput);
        btnConvertiraTexto = findViewById(R.id.btnConvertiraVoz);
        conversor = new ConversorSpeech();
        conversor.init(this);
        btnConvertiraTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = edttextoInput.getText().toString();
                conversor.iniciarCola(texto,0.0f,0.5f);
            }
        });

    }

    @Override
    protected void onDestroy() {
        conversor.apagar();
        super.onDestroy();
    }
}