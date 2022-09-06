package com.acevedo.sem2_maap_appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsuario;
    EditText edtPassword;
    EditText edtEmail;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtPassword);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {



        //pasando parametros al activity registrar
        //capturando datos
        String sUsuario = edtUsuario.getText().toString();
        String sPassword = edtPassword.getText().toString();
        String sEmail = edtEmail.getText().toString();

        if(sUsuario.equals("a@x.com") && sPassword.equals("123")){
            Intent i= new Intent(this, Home.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Credenciales erradas", Toast.LENGTH_SHORT).show();
        }
        if("".equals(sUsuario)){
            edtUsuario.setError("Campo requerido");
            edtUsuario.requestFocus();
        }
        if("".equals(sPassword)){
            edtPassword.setError("Campo requerido");
            edtPassword.requestFocus();
        }

        // mandando datos al mainActivity
//        i.putExtra("nombreUsuario",sUsuario);
//        i.putExtra("passUsuario",sPassword);
//        i.putExtra("emailUsuario",sEmail);


    }
}