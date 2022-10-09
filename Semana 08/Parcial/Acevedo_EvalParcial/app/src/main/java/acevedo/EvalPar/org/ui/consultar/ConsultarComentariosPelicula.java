package acevedo.EvalPar.org.ui.consultar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import acevedo.EvalPar.org.Entidades.Comentario;
import acevedo.EvalPar.org.Entidades.ComentarioAdapter;
import acevedo.EvalPar.org.Entidades.Pelicula;
import acevedo.EvalPar.org.Entidades.PeliculaImagenAdapter;
import acevedo.EvalPar.org.R;
import acevedo.EvalPar.org.UtilAPM.Util;

public class ConsultarComentariosPelicula extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerComentarios;
    TextView txtTitleComentario;
    ArrayList<Comentario> listacomentarios;
    Button btnRegresar;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_comentarios_pelicula);
        listacomentarios = new ArrayList<>();
        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtTitleComentario = findViewById(R.id.txtTitleComentario);
        recyclerComentarios = findViewById(R.id.RecyclerComentarios);
        recyclerComentarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerComentarios.setHasFixedSize(true); // tamaño del recycler
        requestQueue = Volley.newRequestQueue(this);
        cargarListaComentarios();
    }

    private void cargarListaComentarios(){

        progreso = new ProgressDialog(this);
        progreso.setMessage("Buscando Comentarios");
        progreso.show();
        String id_pelicula = getIntent().getStringExtra("idPelicula");
        String title = getIntent().getStringExtra("title");
        txtTitleComentario.setText(title);
        // registrando cliente
        String url = Util.RUTA + "consultar_comentario.php?id_pelicula="+id_pelicula;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Comentario comentario = null;
        progreso.hide();
        JSONArray json = response.optJSONArray("comentario");//vector

        try {
            for(int i = 0; i<json.length();i++){
                comentario = new Comentario(); // inicializar pelicula
                JSONObject jsonObject = null; // por cada fila se debe de inicializar el objeto json
                jsonObject = json.getJSONObject(i);
                comentario.setId(jsonObject.getInt("id"));
                comentario.setCalificacion(jsonObject.getInt("calificacion"));
                comentario.setComentario(jsonObject.getString("comentario"));
                comentario.setId_pelicula(jsonObject.getInt("id_pelicula"));
                listacomentarios.add(comentario); // la lista de peliculas estará almacenando todas las peliculas encontradas
            }
            ComentarioAdapter adapter = new ComentarioAdapter(listacomentarios);
            recyclerComentarios.setAdapter(adapter);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}