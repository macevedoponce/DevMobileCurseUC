package com.example.sensors;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class VozATexto extends AppCompatActivity {
TextView txtTextoDetectado;
ImageButton imgBtnMic, imgBtnSpeak, imgBtnSave, imgBtnRead;
private static final int REQ_CODE_SPEECH_INPUT=100;
ConversorSpeech conversor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voz_atexto);
        txtTextoDetectado = findViewById(R.id.txtTextoDetectado);
        imgBtnMic = findViewById(R.id.imgBtnMic);
        imgBtnSave = findViewById(R.id.imgBtnSave);
        imgBtnSpeak = findViewById(R.id.imgBtnSpeak);
        imgBtnRead = findViewById(R.id.imgBtnRead);

        conversor = new ConversorSpeech();
        conversor.init(this);


        imgBtnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarDeteccion();
            }
        });
        imgBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarArchivo();
            }
        });

        imgBtnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducirArchivo();
            }
        });

        imgBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leerArchivo();
            }
        });

    }

    @Override
    protected void onDestroy() {
        conversor.apagar();
        super.onDestroy();
    }

    private void reproducirArchivo() {
        String texto = txtTextoDetectado.getText().toString();
        conversor.iniciarCola(texto,1.0f,1.5f);
    }

    private void leerArchivo() {
        Toast.makeText(this, "Lectura", Toast.LENGTH_SHORT).show();
        try{
            BufferedReader leerArchivo= new BufferedReader(new InputStreamReader(openFileInput("MiArchivo.txt")));
            String texto= leerArchivo.readLine();
            leerArchivo.close();
            txtTextoDetectado.setText(texto);
        }
        catch(Exception e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();

        }
    }

    private void guardarArchivo() {
        String estado= Environment.getExternalStorageState();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            try{
                String texto=txtTextoDetectado.getText().toString();
                FileOutputStream objEscribirArchivo = null;
                objEscribirArchivo=openFileOutput("MiArchivo.txt", MODE_PRIVATE);
                objEscribirArchivo.write(texto.getBytes());
                objEscribirArchivo.close();
                Toast.makeText(this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
                txtTextoDetectado.setText("");
            }
            catch (Exception e){
                Toast.makeText(this, "Hubo error en la escritura del archivo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void iniciarDeteccion() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // lenguaje por defecto del celular
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora");
        try {
            startActivityForResult(i, REQ_CODE_SPEECH_INPUT); // pueden tener varios resultados
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "Error al Reconocer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && data != null ){
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtTextoDetectado.setText(resultado.get(0));
                }

            }
        }
    }



}