package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsuario;
    EditText edtPassword;
    EditText edtEmail;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i= new Intent(this, MainActivity.class);
        //pasando parametros al activity registrar
        //capturando datos
        String sUsuario = edtUsuario.getText().toString();
        String sPassword = edtPassword.getText().toString();
        String sEmail = edtEmail.getText().toString();
        // mandando datos al mainActivity
        i.putExtra("nombreUsuario",sUsuario);
        i.putExtra("passUsuario",sPassword);
        i.putExtra("emailUsuario",sEmail);

        startActivity(i);
    }
}