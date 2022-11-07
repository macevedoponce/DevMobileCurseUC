package com.acevedo.s11.ui.openFile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.s11.ConversorSpeech;
import com.acevedo.s11.DialogSaveFile;
import com.acevedo.s11.R;
import com.acevedo.s11.ui.rec.rec;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class OpenFile extends Fragment implements DialogSaveFile.EndDialog{

    TextView txtTextoDeArchivo;
    ImageButton imgBtnSearch, imgBtnPlay, imgBtnDelete;
    ConversorSpeech conversor;

    public OpenFile() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_open_file, container, false);
        txtTextoDeArchivo = view.findViewById(R.id.txtTextoDeArchivo);
        imgBtnSearch = view.findViewById(R.id.imgBtnSearch);
        imgBtnPlay = view.findViewById(R.id.imgBtnPlay);
        imgBtnDelete = view.findViewById(R.id.imgBtnDelete);

        conversor = new ConversorSpeech();
        conversor.init(getContext());

        imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //leerArchivo();
                new DialogSaveFile(getContext(), OpenFile.this);

            }
        });

        imgBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducirArchivo();
            }
        });

        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eliminarArchivo();
            }
        });
        return view;
    }

    private void eliminarArchivo() {
        Toast.makeText(getContext(), "Eliminar Archivo no implementado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        conversor.apagar();
        super.onDestroy();
    }

    private void leerArchivo(String edtNameFile) {
        //Toast.makeText(getContext(), "Lectura", Toast.LENGTH_SHORT).show();
        try{
           String nameFile = edtNameFile + ".txt"; //nombre del archivo
            BufferedReader leerArchivo = new BufferedReader(new InputStreamReader(requireContext().openFileInput(nameFile))); //abrir el archivo con el nombre que se le manda
            String texto= leerArchivo.readLine(); //leer por lineas
            leerArchivo.close();
            txtTextoDeArchivo.setText(texto);
        }
        catch(Exception e){
            Toast.makeText(getContext(), "Error al leer el archivo", Toast.LENGTH_SHORT).show();

        }
    }

    private void reproducirArchivo() {
        String texto = txtTextoDeArchivo.getText().toString();
        conversor.iniciarCola(texto,1.0f,1.5f);
    }

    @Override
    public void resultDialog(String edtNameFile) {
        leerArchivo(edtNameFile);
    }
}