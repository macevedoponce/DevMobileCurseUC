package com.example.mapsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.Entidades.Usuario;

import java.util.ArrayList;

import Utilidades.UtilAPM;

public class ListarUsuarios extends AppCompatActivity {
    ListView lstUsuarios;
    SqliteUtil conexion; //conexion con la base de datos
    String[] navesNasa = {"Junio", "Hubble", "Cassini", "Pionner", "Challenger"};
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        lstUsuarios = findViewById(R.id.lstUsuariosSimple);
        conexion = new SqliteUtil(this, UtilAPM.BASE_DATOS,null,1); // conexion con la base de datos
        // se requiere de un adapter
        generarListadoUsuarios();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        lstUsuarios.setAdapter(adapter);

        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                String info = listaUsuarios.get(posicion).toString();
                Toast.makeText(ListarUsuarios.this, info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generarListadoUsuarios() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();
        try {
            Cursor cursor = db.rawQuery("select * from " + UtilAPM.TABLA_USUARIO, null);
            while (cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setId(cursor.getString(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setTelefono(cursor.getString(2));
                listaUsuarios.add(usuario);
            }
            obtenerLista();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerLista() {
        // aplanar datos, todos los datos en una sola fila | no se debe hacer
        listaInformacion = new ArrayList<String>();
        for( int i =0; i<listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getId() + " - " + listaUsuarios.get(i).getNombre()
            + " - " + listaUsuarios.get(i).getTelefono());
        }
    }
}

// ejemplo de lista simple
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_listar_usuarios);
//    lstUsuarios = findViewById(R.id.lstUsuariosSimple);
//    // se requiere de un adapter
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_lista,R.id.txtNombreNave,navesNasa);
//    lstUsuarios.setAdapter(adapter);
//
//    lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
//            Toast.makeText(ListarUsuarios.this, navesNasa[posicion], Toast.LENGTH_SHORT).show();
//        }
//    });
//}