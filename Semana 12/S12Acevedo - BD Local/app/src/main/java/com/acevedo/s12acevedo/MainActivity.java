package com.acevedo.s12acevedo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.s12acevedo.Util.Util;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Pelicula> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this)); //vista lista
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));//vista grid
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        mList = new ArrayList<>();
        fetchData();
    }
    //
    private void fetchData() {
        String url = Util.URL;
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new

                        Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("pelicula");
                                    for(int i = 0 ; i<jsonArray.length() ; i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        int id = jsonObject.getInt("id");
                                        String title = jsonObject.getString("title");
                                        String description = jsonObject.getString("description");
                                        String time = jsonObject.getString("time");
                                        String year = jsonObject.getString("year");
                                        String director = jsonObject.getString("director");
                                        String category = jsonObject.getString("category");
                                        String ruta_imagen = jsonObject.getString("ruta_imagen");
                                        Pelicula post = new Pelicula(id, title, description, time, year, director, category, ruta_imagen);
                                        mList.add(post);
                                    }
                                    PeliculaAdapter adapter = new PeliculaAdapter(MainActivity.this , mList);
                                    adapter.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ShowDialogBox(view);
                                        }
                                    });

                                    recyclerView.setAdapter(adapter);

                                    adapter.notifyDataSetChanged();
                                    // hacer click a un post

                                    //fin hacer click a un post
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void ShowDialogBox(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogoapm);
        TextView image_name = dialog.findViewById(R.id.txtImageName);
        ImageView image = dialog.findViewById(R.id.img);
        Button btn_full = dialog.findViewById(R.id.btnFull);
        Button btn_cerrar = dialog.findViewById(R.id.btnCerrar);
        String title = mList.get(recyclerView.getChildAdapterPosition(view)).getTitle();
        String image_url = mList.get(recyclerView.getChildAdapterPosition(view)).getRuta_imagen();
        Glide.with(this).load(image_url).into(image); //pinta la imagen
        image_name.setText(title);

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,VistaCompleta.class);
                i.putExtra("image_url",image_url);
                startActivity(i);
            }
        });
        dialog.show();
    }
}

//singleton -> ahorro de bateria -> no cargar el dispositivo -> felicidad del usuario