package com.example.mapsqlite1.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.Entidades.Usuario;
import com.example.mapsqlite1.ListarUsuarios;
import com.example.mapsqlite1.R;

import java.util.ArrayList;

import Utilidades.UtilAPM;


public class ListarUsuarioFragment extends Fragment {
    ListView lstUsuarios;
    SqliteUtil conexion; //conexion con la base de datos
    String[] navesNasa = {"Junio", "Hubble", "Cassini", "Pionner", "Challenger"};
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacion;


    public ListarUsuarioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_listar_usuario, container, false);
        lstUsuarios = vista.findViewById(R.id.lstUsuariosSimple);
        conexion = new SqliteUtil(getContext(), UtilAPM.BASE_DATOS,null,1); // conexion con la base de datos
        // se requiere de un adapter
        generarListadoUsuarios();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listaInformacion);
        lstUsuarios.setAdapter(adapter);

        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                String info = listaUsuarios.get(posicion).toString();
                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
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
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
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