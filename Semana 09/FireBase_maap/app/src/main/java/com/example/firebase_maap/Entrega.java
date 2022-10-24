package com.example.firebase_maap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.firebase_maap.Entidades.Pedido;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Entrega extends AppCompatActivity {
    ListView lstViewPedidosEntregar;
    List<Pedido> listaPedido = new ArrayList<>();
    ArrayAdapter<Pedido> arrayAdapterPedidos;
    Spinner spFiltro;
    TextView txtTitulo;

    private DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);
        inicializarFirebase();
        lstViewPedidosEntregar = findViewById(R.id.lstPedidosEntregar);
        spFiltro = findViewById(R.id.spFiltro);
        txtTitulo = findViewById(R.id.txtTitulo);
        //listarPedidos();


        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filtrarPedidos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void filtrarPedidos() {
        DatabaseReference pedidosReference = mDataBase.child("Pedidos");//el objeto pedidosreference apunte al nodo pedido
        //Query pedidosfiltrados =  pedidosReference.orderByChild("estado").equalTo("n");
        String distrito = spFiltro.getSelectedItem().toString();
        Query pedidosDistritoFiltro = pedidosReference.orderByChild("distrito").equalTo(distrito);


//        pedidosfiltrados.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                listaPedido.clear(); //estructura de datos limpiar
//                int n = 0;
//                for (DataSnapshot objSnapshot : snapshot.getChildren()){
//                    Pedido p = objSnapshot.getValue(Pedido.class);
//                    listaPedido.add(p); // aca agregamos lo obtenido desde firebase
//                }
//                arrayAdapterPedidos = new ArrayAdapter<Pedido>(Entrega.this, android.R.layout.simple_list_item_1, listaPedido);
//                lstViewPedidosEntregar.setAdapter(arrayAdapterPedidos);
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

        pedidosDistritoFiltro.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaPedido.clear(); //estructura de datos limpiar
                int n = 0;
                for (DataSnapshot objSnapshot : snapshot.getChildren()){
                    Pedido p = objSnapshot.getValue(Pedido.class);
                    if(p.getEstado() != null){ //solo datos con estado nuevo
                        listaPedido.add(p); // aca agregamos lo obtenido desde firebase
                    }
                    //listaPedido.add(p); // aca agregamos lo obtenido desde firebase
                }
                arrayAdapterPedidos = new ArrayAdapter<Pedido>(Entrega.this, android.R.layout.simple_list_item_1, listaPedido);
                lstViewPedidosEntregar.setAdapter(arrayAdapterPedidos);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        //referencia de la app con la bd
        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDataBase = db.getReference();

    }
}