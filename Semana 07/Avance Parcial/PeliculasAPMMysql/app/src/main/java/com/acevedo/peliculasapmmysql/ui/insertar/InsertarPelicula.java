package com.acevedo.peliculasapmmysql.ui.insertar;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acevedo.peliculasapmmysql.R;
import com.acevedo.peliculasapmmysql.UtilAPM.Util;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;


public class InsertarPelicula extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    TextInputLayout tilID, tilTitle, tilDescription, tilTime, tilYear, tilDirector, tilCategory, tilImagen;
    EditText edtId, edtTitle, edtDescription, edtTime, edtYear, edtDirector, edtCategory, edtImagen;
    Button btnInsertarPelicula;

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
        View vista =  inflater.inflate(R.layout.fragment_insertar_pelicula, container, false);
        requestQueue = Volley.newRequestQueue(getContext());

        tilID = vista.findViewById(R.id.tilIdPelicula);
        tilTitle = vista.findViewById(R.id.tilTitlePelicula);
        tilDescription = vista.findViewById(R.id.tilDescripcionPelicula);
        tilTime = vista.findViewById(R.id.tilDuracionPelicula);
        tilYear = vista.findViewById(R.id.tilYearPelicula);
        tilDirector = vista.findViewById(R.id.tilDirectorPelicula);
        tilCategory = vista.findViewById(R.id.tilCategoriaPelicula);
        tilImagen = vista.findViewById(R.id.tilImagenPelicula);

        edtId = vista.findViewById(R.id.edtIdPelicula);
        edtTitle = vista.findViewById(R.id.edtTitlePelicula);
        edtDescription = vista.findViewById(R.id.edtDescripcionPelicula);
        edtTime = vista.findViewById(R.id.edtDuracionPelicula);
        edtYear = vista.findViewById(R.id.edtYearPelicula);
        edtDirector = vista.findViewById(R.id.edtDirectorPelicula);
        edtCategory = vista.findViewById(R.id.edtCategoriaPelicula);
        edtImagen = vista.findViewById(R.id.edtImagenPelicula);

        btnInsertarPelicula = vista.findViewById(R.id.btnInsertarPelicula);
        btnInsertarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPelicula();
            }
        });
        return vista;
    }

    private void registrarPelicula() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Insertando");
        progreso.show();
        String idc = edtId.getText().toString();
        String titlec = edtTitle.getText().toString();
        String descriptionc = edtDescription.getText().toString();
        String timec = edtTime.getText().toString();
        String yearc = edtYear.getText().toString();
        String directorc = edtDirector.getText().toString();
        String categoryc = edtCategory.getText().toString();
        String imagenc = edtImagen.getText().toString();

        String url = Util.RUTA +
                "insertar_pelicula.php?id="+idc+
                "&title=" + titlec +
                "&description=" + descriptionc +
                "&time=" + timec +
                "&year=" + yearc +
                "&director=" + directorc +
                "&category=" + categoryc +
                "&ruta_imagen=" + imagenc;

        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(getContext(), "Registro Correcto", Toast.LENGTH_SHORT).show();
        edtId.setText("");
        edtTitle.setText("");
        edtDescription.setText("");
        edtTime.setText("");
        edtYear.setText("");
        edtDirector.setText("");
        edtCategory.setText("");
        edtImagen.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), "Error de inserción", Toast.LENGTH_SHORT).show();
        Log.i("error",error.toString());
    }
}