package acevedo.EvalFin.org.MediappRepartidores.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acevedo.EvalFin.org.Adapter.PedidoAdapter;
import acevedo.EvalFin.org.Adapter.PedidoAdapterRepartidor;
import acevedo.EvalFin.org.Clases.Pedido;
import acevedo.EvalFin.org.Mediapp.MisPedidos;
import acevedo.EvalFin.org.MediappRepartidores.verRutaPedido;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;


public class InicioRepartidorFragment extends Fragment {

    TextView txtVerDireccion;
    Double mLatDestino;
    Double mLongDestino;

    RecyclerView recyclerPedidos;

    RequestQueue requestQueue;
    List<Pedido> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_inicio_repartidor, container, false);

        recyclerPedidos = vista.findViewById(R.id.recyclerPedidos);
        recyclerPedidos.setHasFixedSize(true);
        recyclerPedidos.setLayoutManager(new LinearLayoutManager(getContext())); //vista lista
        requestQueue = Volley.newRequestQueue(getContext());
        mList = new ArrayList<>();
        cargarPedidos();

//        txtVerDireccion = vista.findViewById(R.id.txtVerDireccion);
//        txtVerDireccion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mLatDestino= -12.040881885255898;
//                mLongDestino= -75.19286162874772;
////
////                ObtenerRuta(mLatOrigen, mLongOrigen, mLatDestino, mLongDestino);
//
//                Intent i = new Intent(getContext(), verRutaPedido.class);
//                i.putExtra("mLatDestino",mLatDestino);
//                i.putExtra("mLongDestino",mLongDestino);
//                startActivity(i);
//            }
//        });
        return vista;
    }

    private void cargarPedidos() {
        String url = Util.RUTA+"list_pedidos.php?"+"&estado=0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        Double latitud = jsonObject.getDouble("latitud");
                        Double longitud = jsonObject.getDouble("longitud");
                        String persona_recepcion = jsonObject.getString("persona_recepcion");
                        String celular_persona_recepcion = jsonObject.getString("celular_persona_recepcion");
                        String total = jsonObject.getString("total");
                        String fecha = jsonObject.getString("fecha");
                        String cantidad = jsonObject.getString("cantidad");
                        String precio_unitario = jsonObject.getString("pre_unit");
                        String nombreProducto = jsonObject.getString("nombreProducto");
                        String img_url = jsonObject.getString("img_url");
                        int estado = jsonObject.getInt("estado");
                        Pedido pedido = new Pedido(id, estado, longitud, latitud, persona_recepcion, celular_persona_recepcion, total, fecha, cantidad, nombreProducto,precio_unitario, img_url);
                        mList.add(pedido);
                    }
                    PedidoAdapterRepartidor adapter = new PedidoAdapterRepartidor(getContext(), mList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //aca poner lo que quieres que se realice al hacer click en el cardView
                            verDireccion(view);
                        }
                    });

                    recyclerPedidos.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se encontro pedidos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void verDireccion(View view) {
        double mLatDestino = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getLatitud();
        double mLongDestino = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getLongitud();
        int id_pedido = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getId();

        Intent i = new Intent(getContext(), verRutaPedido.class);
        i.putExtra("mLatDestino",mLatDestino);
        i.putExtra("mLongDestino",mLongDestino);
        i.putExtra("id_pedido",id_pedido+"");
        startActivity(i);
    }


}