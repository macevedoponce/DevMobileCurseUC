package com.example.acevedobank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.acevedobank.Entidades.Cliente;

import org.json.JSONArray;
import org.json.JSONObject;

import UtilAPM.Util;

public class ConsultarCliente extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText edtIdCliente;
    TextView txtNombres, txtApellidos,txtEmail, txtTelefono;
    Button btnAgregar;
    ImageView imgCliente;

    ProgressDialog progreso;
    RequestQueue requestQueue; //cola de requerimiento -> gestiona la espera las respuestas del servidor
    JsonObjectRequest jsonObjectRequest; //permite recibir lo que envia el web service y adaptar a como lo necesite
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        edtIdCliente = findViewById(R.id.edtIdClienteConsultar);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnAgregar = findViewById(R.id.btnBuscar);
        imgCliente = findViewById(R.id.imgCliente);
        requestQueue = Volley.newRequestQueue(this);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarCliente();
            }
        });
    }

    private void buscarCliente() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Buscando Registro");
        progreso.show();
        //obteniendo datos desde el layout
        String idc = edtIdCliente.getText().toString();
//        String nomc = edtNombres.getText().toString();
//        String apec = edtApellidos.getText().toString();
//        String emac = edtEmail.getText().toString();
//        String telc = edtTelefono.getText().toString();
        // registrando cliente
        String url = Util.RUTA + "consultar_cliente_imagen.php?idcliente=" + idc;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        //cuando se tenga respuesta hacer lo que sigue
        Toast.makeText(this, "Cliente ubicado", Toast.LENGTH_SHORT).show();
        progreso.hide();
        Cliente cliente = new Cliente(); //objeto cliente
        JSONArray json = response.optJSONArray("cliente");//vector
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            cliente.setNombres(jsonObject.getString("nombres"));
            cliente.setApellidos(jsonObject.getString("apellidos"));
            cliente.setEmail(jsonObject.getString("email"));
            cliente.setTelefono(jsonObject.getString("telefono"));
            cliente.setDataImagen(jsonObject.getString("imagen"));

        }catch(Exception e){
            e.printStackTrace();
        }
        edtIdCliente.setText("");
        txtNombres.setText(cliente.getNombres());
        txtApellidos.setText(cliente.getApellidos());
        txtEmail.setText(cliente.getEmail());
        txtTelefono.setText(cliente.getTelefono());
        if(cliente.getImagen()!=null){
            imgCliente.setImageBitmap(cliente.getImagen());
        }
        else{
            imgCliente.setImageResource(R.drawable.img_base);
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //cuando no hay respuesta, hacer lo que sigue
        progreso.hide();
        Toast.makeText(this, "Cliente no encontrado", Toast.LENGTH_SHORT).show();
        Log.i("error", error.toString());// el error se imprima en pantalla
    }
}