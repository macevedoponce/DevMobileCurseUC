package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    EditText edtProfesion;
    EditText edtDireccion;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        edtProfesion = findViewById(R.id.edtProfesion);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
    }
    
    public void registrar(View view){
        String sProfesion = edtProfesion.getText().toString();
        String sDireccion = edtDireccion.getText().toString();
        //validación de datos
        if("".equals(sProfesion)){
            edtProfesion.setError("Campo requerido");
            edtProfesion.requestFocus();
        }
        if("".equals(sDireccion)){
            edtDireccion.setError("Campo requerido");
            edtDireccion.requestFocus();
        }
        Toast.makeText(this, "Registrar funcionando", Toast.LENGTH_SHORT).show();
    }
}