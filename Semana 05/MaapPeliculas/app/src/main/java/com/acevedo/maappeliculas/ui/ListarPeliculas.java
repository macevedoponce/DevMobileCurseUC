package com.acevedo.maappeliculas.ui;

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

import com.acevedo.maappeliculas.Entidades.Pelicula;
import com.acevedo.maappeliculas.Entidades.SqliteUtil;
import com.acevedo.maappeliculas.R;
import com.acevedo.maappeliculas.Utilidades.UtilAPM;

import java.util.ArrayList;


public class ListarPeliculas extends Fragment {
    ListView lstPeliculas;
    SqliteUtil conexion;
    ArrayList<Pelicula> listaPeliculas;
    ArrayList<String> listaInformacion;

    public ListarPeliculas() {
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
        View vista = inflater.inflate(R.layout.fragment_listar_peliculas, container, false);
        lstPeliculas = vista.findViewById(R.id.lstPeliculasSimple);
        conexion = new SqliteUtil(getContext(), UtilAPM.BASE_DATOS,null,1);
        generarListadoPeliculas();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item_lista,R.id.txtDescription,listaInformacion);
        lstPeliculas.setAdapter(adapter);

//        lstPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String info = listaPeliculas.get(position).toString();
//                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//        });
        return vista;
    }

    private void generarListadoPeliculas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Pelicula pelicula = null;
        listaPeliculas = new ArrayList<Pelicula>();
        try{
            Cursor cursor = db.rawQuery("select * from " + UtilAPM.TABLA_PELICULA,null);
            while (cursor.moveToNext()){
                pelicula = new Pelicula();
                pelicula.setId(cursor.getString(0));
                pelicula.setTitle(cursor.getString(1));
                pelicula.setDescription(cursor.getString(2));
                listaPeliculas.add(pelicula);
            }
            obtenerLista();
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for(int i = 0; i<listaPeliculas.size(); i++){
            listaInformacion.add(listaPeliculas.get(i).getId() + " - " + listaPeliculas.get(i).getTitle()+ " - " + listaPeliculas.get(i).getDescription());
        }
    }
}