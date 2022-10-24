package com.acevedo.avancefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.acevedo.avancefinal.Entidades.Pedido;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarPedido extends AppCompatActivity {
    EditText edtDestinatario, edtDireccion, edtDescripcion;
    Button btnRegistrar;
    Spinner spTipo;

    private DatabaseReference mDataBase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        edtDestinatario = findViewById(R.id.edtDestinatario);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnRegistrar = findViewById(R.id.btnRegistrarPedido);
        spTipo = findViewById(R.id.spTipo);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDataBase = database.getReference();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPedido();
            }
        });
    }

    private void registrarPedido() {
        String nombre = edtDestinatario.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String tipo = spTipo.getSelectedItem().toString();
        String estado = "n";
        String fecharegistro = "21/10/2022";
        Double latitud = getIntent().getDoubleExtra("latitud",0.0);
        Double longitud = getIntent().getDoubleExtra("longitud",0.0);
        //String longitud = getIntent().getStringExtra("longitud");
        
        Pedido pedido = new Pedido(1,nombre,descripcion,tipo,latitud,longitud,estado,fecharegistro);
        String id = mDataBase.push().getKey();
        mDataBase.child("Pedidos").child(id).setValue(pedido);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}