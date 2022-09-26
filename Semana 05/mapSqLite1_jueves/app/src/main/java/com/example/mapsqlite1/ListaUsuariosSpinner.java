package com.example.mapsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.Entidades.Usuario;

import java.util.ArrayList;

import Utilidades.UtilAPM;

public class ListaUsuariosSpinner extends AppCompatActivity {
    Spinner spiUsuarios;
    TextView txtId, txtNombre, txtTelefono;
    SqliteUtil conexion; //conexion con la base de datos
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios_spinner);
        spiUsuarios = findViewById(R.id.spiUsuarios);
        txtId = findViewById(R.id.txtIdSP);
        txtNombre = findViewById(R.id.txtNombreSP);
        txtTelefono = findViewById(R.id.txtTelefonoSP);
        conexion = new SqliteUtil(this, UtilAPM.BASE_DATOS,null,1); // conexion con la base de datos
        // se requiere de un adapter
        generarListadoUsuarios();
        //ojo formato de cada item
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaInformacion);
        spiUsuarios.setAdapter(adapter);

        spiUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                txtId.setText(listaUsuarios.get(posicion).getId().toString());
                txtNombre.setText(listaUsuarios.get(posicion).getNombre().toString());
                txtTelefono.setText(listaUsuarios.get(posicion).getTelefono().toString());
//                txtId.setText(String.valueOf(posicion).toString());
//                txtNombre.setText(String.valueOf(posicion).toString());
//                txtTelefono.setText(String.valueOf(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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