package com.example.acevedobank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import UtilAPM.Util;

public class InsertarCliente extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    TextInputLayout tilId,tilNombres,tilApellidos, tilEmail, tilTelefono;
    EditText edtId, edtNombres, edtApellidos, edtEmail, edtTelefono;
    Button btnRegistrarCliente;

    ProgressDialog progreso;
    RequestQueue requestQueue; //cola de requerimiento -> gestiona la espera las respuestas del servidor
    JsonObjectRequest jsonObjectRequest; //permite recibir lo que envia el web service y adaptar a como lo necesite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_cliente);
        //inicializa requestQueue
        requestQueue = Volley.newRequestQueue(this);

        tilId = findViewById(R.id.tilIdCliente);
        tilNombres = findViewById(R.id.tilNombreCliente);
        tilApellidos = findViewById(R.id.tilApellidoCliente);
        tilEmail = findViewById(R.id.tilEmailCliente);
        tilTelefono = findViewById(R.id.tilTelefonoCliente);

        edtId = tilId.getEditText().findViewById(R.id.edtIdCliente);
        edtNombres = tilNombres.getEditText().findViewById(R.id.edtNombreCliente);
        edtApellidos = tilApellidos.getEditText().findViewById(R.id.edtApellidoCliente);
        edtEmail = tilEmail.getEditText().findViewById(R.id.edtEmailCliente);
        edtTelefono = tilTelefono.getEditText().findViewById(R.id.edtTelefonoCliente);

        btnRegistrarCliente = findViewById(R.id.btnRegistrarCliente);
        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCliente();
            }
        });

    }

    private void registrarCliente() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Insertando");
        progreso.show();
        //obteniendo datos desde el layout
        String idc = edtId.getText().toString();
        String nomc = edtNombres.getText().toString();
        String apec = edtApellidos.getText().toString();
        String emac = edtEmail.getText().toString();
        String telc = edtTelefono.getText().toString();
        // registrando cliente
        String url = Util.RUTA + "insertar_cliente.php?id=" +
                idc + "&nombres="+nomc +"&apellidos="+apec +"&email="+emac+"&telefono="+telc;
        url = url.replace(" ","%20"); // reemplazamos los espacios en blanco para no tener problemas a la hora de registrar
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,this, this);
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        //cuando se tenga respuesta hacer lo que sigue
        progreso.hide();
        Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
        edtId.setText("");
        edtNombres.setText("");
        edtApellidos.setText("");
        edtEmail.setText("");
        edtTelefono.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //cuando no hay respuesta, hacer lo que sigue
        progreso.hide();
        Toast.makeText(this, "Error de inserción", Toast.LENGTH_SHORT).show();
        Log.i("error", error.toString());// el error se imprima en pantalla
    }
}