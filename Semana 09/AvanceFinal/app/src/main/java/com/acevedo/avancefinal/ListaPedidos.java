package com.acevedo.avancefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.acevedo.avancefinal.Entidades.Pedido;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos extends AppCompatActivity {
    ListView lstViewPedidosEntregar;
    List<Pedido> listaPedido = new ArrayList<>();
    ArrayAdapter<Pedido> arrayAdapterPedidos;
    Spinner spTipoPedido;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        inicializarFirebase();
        lstViewPedidosEntregar = findViewById(R.id.lstPedidosEntregar);
        spTipoPedido = findViewById(R.id.spFiltroPedido);

        spTipoPedido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        String tipo = spTipoPedido.getSelectedItem().toString();
        Query pedidosDistritoFiltro = pedidosReference.orderByChild("tipo").equalTo(tipo);


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
                }
                arrayAdapterPedidos = new ArrayAdapter<Pedido>(ListaPedidos.this, android.R.layout.simple_list_item_1, listaPedido);
                lstViewPedidosEntregar.setAdapter(arrayAdapterPedidos);
                lstViewPedidosEntregar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String destinatario = listaPedido.get(i).getDestinatario();
                        String tipo = listaPedido.get(i).getTipo();
                        String descripcion = listaPedido.get(i).getDescripcion();
                        Double longitud = listaPedido.get(i).getLongitud();
                        Double latitud = listaPedido.get(i).getLatitud();

                        Intent intent = new Intent(ListaPedidos.this,VerPedidoMapa.class);
                        intent.putExtra("destinatario",destinatario);
                        intent.putExtra("tipo",tipo);
                        intent.putExtra("descripcion",descripcion);
                        intent.putExtra("longitud",longitud);
                        intent.putExtra("latitud",latitud);
                        startActivity(intent);


                    }
                });
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