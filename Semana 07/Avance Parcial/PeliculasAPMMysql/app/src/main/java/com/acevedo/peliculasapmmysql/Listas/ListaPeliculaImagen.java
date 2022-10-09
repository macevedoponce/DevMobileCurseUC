package com.acevedo.peliculasapmmysql.Listas;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acevedo.peliculasapmmysql.Entidades.Pelicula;
import com.acevedo.peliculasapmmysql.Entidades.PeliculaImagenAdapter;
import com.acevedo.peliculasapmmysql.R;
import com.acevedo.peliculasapmmysql.UtilAPM.Util;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaPeliculaImagen extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerPeliculas;
    ArrayList<Pelicula> listaPeliculas;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_lista_pelicula_imagen, container, false);
        listaPeliculas = new ArrayList<>();
        recyclerPeliculas = vista.findViewById(R.id.RecyclerPeliculas);
        recyclerPeliculas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPeliculas.setHasFixedSize(true); // tamaño del recycler
        requestQueue = Volley.newRequestQueue(getContext());
        cargarListaPeliculas();
        return vista;
    }

    private void cargarListaPeliculas() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Buscando Pelicula");
        progreso.show();

        // registrando cliente
        String url = Util.RUTA + "consultar_lista_imagenes.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Pelicula pelicula = null;
        progreso.hide();
        JSONArray json = response.optJSONArray("pelicula");//vector

        try {
            for(int i = 0; i<json.length();i++){
                pelicula = new Pelicula(); // inicializar pelicula
                JSONObject jsonObject = null; // por cada fila se debe de inicializar el objeto json
                jsonObject = json.getJSONObject(i);
                pelicula.setTitle(jsonObject.getString("title"));
                pelicula.setDescription(jsonObject.getString("description"));
                pelicula.setTime(jsonObject.getString("time"));
                pelicula.setYear(jsonObject.getString("year"));
                pelicula.setDirector(jsonObject.getString("director"));
                pelicula.setCategory(jsonObject.getString("category"));
                pelicula.setDataImagen(jsonObject.getString("imagen"));
                listaPeliculas.add(pelicula); // la lista de peliculas estará almacenando todas las peliculas encontradas
            }
            PeliculaImagenAdapter adapter = new PeliculaImagenAdapter(listaPeliculas);
            recyclerPeliculas.setAdapter(adapter);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //cuando no hay respuesta, hacer lo que sigue
        progreso.hide();
        Toast.makeText(getContext(), "Pelicula no encontrada", Toast.LENGTH_SHORT).show();
        Log.i("error", error.toString());// el error se imprima en pantalla
    }
}