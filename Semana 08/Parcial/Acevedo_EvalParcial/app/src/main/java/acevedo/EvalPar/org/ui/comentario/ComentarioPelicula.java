package acevedo.EvalPar.org.ui.comentario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import acevedo.EvalPar.org.R;
import acevedo.EvalPar.org.UtilAPM.Util;
import acevedo.EvalPar.org.ui.consultar.ConsultarPelicula;

public class ComentarioPelicula extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    TextInputLayout tilComentario;
    EditText edtComentario;
    Button btnAddComentario;
    ImageButton btnVoler;
    RatingBar ratCalificacion;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_pelicula);
        requestQueue = Volley.newRequestQueue(this);
        tilComentario = findViewById(R.id.tilComentario);
        edtComentario = findViewById(R.id.edtComentario);
        ratCalificacion = findViewById(R.id.ratCalificacion);
        btnVoler = findViewById(R.id.btnRegresar);
        btnVoler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnAddComentario = findViewById(R.id.btnAddComentario);
        btnAddComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarComentario();
            }
        });
    }

    private void registrarComentario() {
        String idPelicula = getIntent().getStringExtra("idPelicula");
        String comentarioc = edtComentario.getText().toString();
        Float calificacionc = ratCalificacion.getRating();
        if(comentarioc.length() > 1  ){
            String url = Util.RUTA + "insertar_comentario.php?" +
                    "calificacion="+calificacionc+
                    "&comentario=" +comentarioc+
                    "&id_pelicula=" +idPelicula;

            url = url.replace(" ","%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
            requestQueue.add(jsonObjectRequest);
        }else{
            tilComentario.setError("No se permite comentarios vacios");
        }
        

    }


    @Override
    public void onResponse(JSONObject response) {
        //progreso.hide();  -> hace que la aplicación vuelva a iniciarse
        edtComentario.setText("");
        ratCalificacion.setRating(0);
        Toast.makeText(this, "Comentario Registrado", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //progreso.hide();
        Toast.makeText(this, "Error de inserción", Toast.LENGTH_SHORT).show();
        Log.i("error",error.toString());
    }
}