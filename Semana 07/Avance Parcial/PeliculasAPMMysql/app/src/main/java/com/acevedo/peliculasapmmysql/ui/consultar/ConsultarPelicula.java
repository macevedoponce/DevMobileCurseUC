package com.acevedo.peliculasapmmysql.ui.consultar;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.peliculasapmmysql.Entidades.Pelicula;
import com.acevedo.peliculasapmmysql.R;
import com.acevedo.peliculasapmmysql.UtilAPM.Util;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;


public class ConsultarPelicula extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    TextInputLayout tilId;
    EditText edtId;
    Button btnBuscar;
    TextView txtTitle, txtDescription, txtTime, txtYear, txtDirector, txtCategory;
    ImageView imgPelicula;

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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_consultar_pelicula, container, false);
        tilId = vista.findViewById(R.id.tilIdPeliculaConsulta);
        edtId = vista.findViewById(R.id.edtIdPeliculaConsulta);
        txtTitle = vista.findViewById(R.id.txtTitle);
        txtDescription = vista.findViewById(R.id.txtDescription);
        txtTime = vista.findViewById(R.id.txtTime);
        txtYear = vista.findViewById(R.id.txtYear);
        txtDirector = vista.findViewById(R.id.txtDirector);
        txtCategory = vista.findViewById(R.id.txtCategory);
        imgPelicula = vista.findViewById(R.id.imgPelicula);
        requestQueue = Volley.newRequestQueue(getContext());
        btnBuscar = vista.findViewById(R.id.btnBuscarPelicula);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPelicula();
            }
        });
        return vista;
    }
    private void buscarPelicula() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Buscando Pelicula");
        progreso.show();
        //obteniendo datos desde el layout
        String idc = edtId.getText().toString();
        // registrando cliente
        String url = Util.RUTA + "consultar_pelicula_imagen.php?id=" + idc;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        //cuando se tenga respuesta hacer lo que sigue
        Toast.makeText(getContext(), "Pelicula encontrada", Toast.LENGTH_SHORT).show();
        progreso.hide();
        Pelicula pelicula = new Pelicula(); //objeto cliente
        JSONArray json = response.optJSONArray("pelicula");//vector
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            pelicula.setTitle(jsonObject.getString("title"));
            pelicula.setDescription(jsonObject.getString("description"));
            pelicula.setTime(jsonObject.getString("time"));
            pelicula.setYear(jsonObject.getString("year"));
            pelicula.setDirector(jsonObject.getString("director"));
            pelicula.setCategory(jsonObject.getString("category"));
            pelicula.setDataImagen(jsonObject.getString("imagen"));

        }catch(Exception e){
            e.printStackTrace();
        }
        edtId.setText("");
        txtTitle.setText(pelicula.getTitle());
        txtDescription.setText(pelicula.getDescription());
        txtTime.setText(pelicula.getTime());
        txtYear.setText(pelicula.getYear());
        txtDirector.setText(pelicula.getDirector());
        txtCategory.setText(pelicula.getCategory());
        if(pelicula.getImagen()!=null){
            imgPelicula.setImageBitmap(pelicula.getImagen());
        }
        else{
            imgPelicula.setImageResource(R.drawable.img_base);
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