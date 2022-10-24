package com.example.firebase_maap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firebase_maap.Entidades.Pedido;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtDestinatario, edtDireccion, edtDescripcion;
    Button btnRegistrar, btnVerPedidos;
    Spinner spDistrito, spTipo;
    ListView lstPedidos;

    private List<Pedido> listPedidos = new ArrayList<Pedido>();
    ArrayAdapter<Pedido> arrayAdapterPedidos;

    private DatabaseReference mDataBase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtDestinatario = findViewById(R.id.edtDestinatario);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnRegistrar = findViewById(R.id.btnRegistrarPedido);
        btnVerPedidos = findViewById(R.id.btnVerPedidos);
        btnVerPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Entrega.class);
                startActivity(i);
            }
        });
        spDistrito = findViewById(R.id.spDistrito);
        spTipo = findViewById(R.id.spTipo);
        lstPedidos = findViewById(R.id.lstPedidos);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDataBase = database.getReference();

        listarPedidos();
        
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPedido();
            }
        });
    }

    private void listarPedidos() {
        mDataBase.child("Pedidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //cuando los datos cambian
                listPedidos.clear();
                int n = 0;
                for (DataSnapshot objSnapshot : snapshot.getChildren()){
                    Pedido p = objSnapshot.getValue(Pedido.class);
                    listPedidos.add(p);
                }
                arrayAdapterPedidos = new ArrayAdapter<Pedido>(MainActivity.this, android.R.layout.simple_list_item_1, listPedidos);
                lstPedidos.setAdapter(arrayAdapterPedidos);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        }); //actualiza automaticamente la recepecion de epdidos
    }

    private void registrarPedido() {
        String nombre = edtDestinatario.getText().toString();
        String direccion = edtDireccion.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String tipo = spTipo.getSelectedItem().toString();
        String distrito =spDistrito.getSelectedItem().toString();
        //nuevos
        String estado = "n";
        String fechaRegistro = "17/10/2022";

        Pedido pedido = new Pedido(1,nombre,direccion,descripcion,tipo,distrito,estado,fechaRegistro);
        String id = mDataBase.push().getKey();
        mDataBase.child("Pedidos").child(id).setValue(pedido);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

    }
}