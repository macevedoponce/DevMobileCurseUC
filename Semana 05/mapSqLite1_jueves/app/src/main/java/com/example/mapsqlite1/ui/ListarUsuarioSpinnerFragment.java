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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.Entidades.Usuario;
import com.example.mapsqlite1.R;

import java.util.ArrayList;

import Utilidades.UtilAPM;


public class ListarUsuarioSpinnerFragment extends Fragment {
    Spinner spiUsuarios;
    TextView txtId, txtNombre, txtTelefono;
    SqliteUtil conexion; //conexion con la base de datos
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacion;


    public ListarUsuarioSpinnerFragment() {
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
        View vista = inflater.inflate(R.layout.fragment_listar_usuario_spinner, container, false);
        spiUsuarios = vista.findViewById(R.id.spiUsuarios);
        txtId = vista.findViewById(R.id.txtIdSP);
        txtNombre = vista.findViewById(R.id.txtNombreSP);
        txtTelefono = vista.findViewById(R.id.txtTelefonoSP);
        conexion = new SqliteUtil(getContext(), UtilAPM.BASE_DATOS,null,1); // conexion con la base de datos
        // se requiere de un adapter
        generarListadoUsuarios();
        //ojo formato de cada item
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,listaInformacion);
        spiUsuarios.setAdapter(adapter);

        spiUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                txtId.setText(listaUsuarios.get(posicion).getId().toString());
                txtNombre.setText(listaUsuarios.get(posicion).getNombre().toString());
                txtTelefono.setText(listaUsuarios.get(posicion).getTelefono().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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