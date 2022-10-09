package acevedo.EvalPar.org.ui.consultar;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import acevedo.EvalPar.org.Entidades.Pelicula;
import acevedo.EvalPar.org.R;
import acevedo.EvalPar.org.UtilAPM.Util;
import acevedo.EvalPar.org.ui.comentario.ComentarioPelicula;


public class ConsultarPelicula extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    TextInputLayout tilTitle;
    EditText edtTitle;
    Button btnBuscar,btnAddComentario;
    TextView txtTitle, txtDescription, txtTime, txtYear, txtDirector, txtCategory;
    ImageView imgPelicula;
    String idPelicula = "";

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

        View vista =inflater.inflate(R.layout.fragment_consultar_pelicula, container, false);
        tilTitle = vista.findViewById(R.id.tilTitlePeliculaConsulta);
        edtTitle = vista.findViewById(R.id.edtTitlePeliculaConsulta);
        txtTitle = vista.findViewById(R.id.txtTitle);
        txtDescription = vista.findViewById(R.id.txtDescription);
        txtTime = vista.findViewById(R.id.txtTime);
        txtYear = vista.findViewById(R.id.txtYear);
        txtDirector = vista.findViewById(R.id.txtDirector);
        txtCategory = vista.findViewById(R.id.txtCategory);
        imgPelicula = vista.findViewById(R.id.imgPelicula);
        requestQueue = Volley.newRequestQueue(getContext());
        btnAddComentario = vista.findViewById(R.id.btnAddComentario);
        btnAddComentario.setEnabled(false);//prueba
        btnAddComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTitle.getText().length() <= 1) {
                    Toast.makeText(getContext(), "necesita buscar una pelicula", Toast.LENGTH_SHORT).show();
                } else {addComentario();}
                }
            });

        btnBuscar = vista.findViewById(R.id.btnBuscarPelicula);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPelicula();
            }
        });
        return vista;
    }

    private void addComentario() {
        //se deberia de llamar a otro fragment
        //pasar dato de idPelicula al nuevo fragment
        Intent i= new Intent(getContext(), ComentarioPelicula.class);
        i.putExtra("idPelicula",idPelicula);
        startActivity(i);
    }

    private void buscarPelicula() {

        //obteniendo datos desde el layout
        String titlec = edtTitle.getText().toString();

        if(titlec.length() > 0){
            String url = Util.RUTA + "consultar_pelicula_imagen.php?title=" + titlec;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
            requestQueue.add(jsonObjectRequest);
            progreso = new ProgressDialog(getContext());
            progreso.setMessage("Buscando Pelicula");
            progreso.show();
        }else{
            tilTitle.setError("Puede ingresar solo una parte del nombre de la pelicula");
        }


    }

    @Override
    public void onResponse(JSONObject response) {
        //cuando se tenga respuesta hacer lo que sigue
        //Toast.makeText(getContext(), "Pelicula encontrada", Toast.LENGTH_SHORT).show();
        progreso.hide();
        Pelicula pelicula = new Pelicula(); //objeto cliente
        JSONArray json = response.optJSONArray("pelicula");//vector
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            pelicula.setId(jsonObject.getString("id"));
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
        idPelicula = pelicula.getId().toString();
        edtTitle.setText("");
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
        btnAddComentario.setEnabled(true);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), "Pelicula no encontrada", Toast.LENGTH_SHORT).show();
        Log.i("error", error.toString());
    }

}