package com.acevedo.s11.ui.rec;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.s11.ConversorSpeech;
import com.acevedo.s11.DialogSaveFile;
import com.acevedo.s11.R;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class rec extends Fragment implements DialogSaveFile.EndDialog {

    TextView txtTextoDetectado,txtRuta;
    ImageButton imgBtnRec, imgBtnSave, imgBtnPlay;
    private static final int REQ_CODE_SPEECH_INPUT=100;
    ConversorSpeech conversor;


    public rec() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rec, container, false);
        txtTextoDetectado = view.findViewById(R.id.txtTextoDetectado);
        imgBtnRec = view.findViewById(R.id.imgBtnRec);
        imgBtnSave = view.findViewById(R.id.imgBtnSave);
        imgBtnPlay = view.findViewById(R.id.imgBtnPlay);
        txtRuta = view.findViewById(R.id.txtRuta);
        conversor = new ConversorSpeech();
        conversor.init(getContext());

        imgBtnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarDeteccion();
            }
        });

        imgBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducirArchivo();
            }
        });

        imgBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextoDetectado.getText().toString() != "") {
                    new DialogSaveFile(getContext(), rec.this);
                }
                else{
                    Toast.makeText(getContext(), "No se encuentra texto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void guardarArchivo(String edtNameFile) {
        String estado= Environment.getExternalStorageState();
        String nameFile = edtNameFile+".txt";
//        Toast.makeText(getContext(), estado, Toast.LENGTH_SHORT).show();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            try{
                String texto=txtTextoDetectado.getText().toString();
                FileOutputStream objEscribirArchivo = null;
                objEscribirArchivo = getContext().openFileOutput(nameFile, Context.MODE_PRIVATE);
                objEscribirArchivo.write(texto.getBytes());
                objEscribirArchivo.close();
                String Ruta = getContext().getFilesDir() + "/" + nameFile;
                txtRuta.setText(Ruta);
                Toast.makeText(getContext(), "Guardado" , Toast.LENGTH_SHORT).show();
                txtTextoDetectado.setText("");
            }
            catch (Exception e){
                Toast.makeText(getContext(), "Hubo error en la escritura del archivo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reproducirArchivo() {
        String texto = txtTextoDetectado.getText().toString();
        conversor.iniciarCola(texto,1.0f,1.0f);
    }

    @Override
    public void onDestroy() {
        conversor.apagar();
        super.onDestroy();
    }

    private void iniciarDeteccion() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // lenguaje por defecto del celular
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora");
        try {
            startActivityForResult(i, REQ_CODE_SPEECH_INPUT); // pueden tener varios resultados
        }catch (ActivityNotFoundException e){
            Toast.makeText(getContext(), "Error al Reconocer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
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

    @Override
    public void resultDialog(String edtNameFile) {
        guardarArchivo(edtNameFile);
    }
}