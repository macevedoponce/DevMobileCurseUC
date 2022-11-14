package com.example.s12miercolesacevedo;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
    private List<Item> mList;
    EditText edtTitulo;
    Button btnSearch;
    String url="https://pixabay.com/api/?key=31212648-ac719a1658c6476a16b2dd2a3&q=computadora&image_type=all&pretty=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTitulo = findViewById(R.id.edtTitulo);
        recyclerView = findViewById(R.id.recyclerview);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        mList = new ArrayList<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String g = edtTitulo.getText().toString();
                if(g != ""){
                    url = "https://pixabay.com/api/?key=31212648-ac719a1658c6476a16b2dd2a3&q="+g+"&image_type=all&pretty=true";
                    fetchData(url);
                }
            }
        });
        fetchData(url);
    }
    //
    private void fetchData(String url) {


        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new

                        Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("hits");
                                    for(int i = 0 ; i<jsonArray.length() ; i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String imageUrlPreview = jsonObject.getString("previewURL");
                                        String largeImageURL = jsonObject.getString("largeImageURL");
                                        int likes = jsonObject.getInt("likes");
                                        String tags = jsonObject.getString("tags");
                                        String user = jsonObject.getString("user");
                                        String type = jsonObject.getString("type");
                                        Item post = new Item(imageUrlPreview,largeImageURL, tags , user, type, likes);
                                        mList.add(post);
                                    }
                                    PostAdapter adapter = new PostAdapter(MainActivity.this , mList);
                                    adapter.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ShowDialogBox(view);
                                        }
                                    });
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
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

        TextView txtTags = dialog.findViewById(R.id.txtTags);
        TextView txtUser = dialog.findViewById(R.id.txtUser);
        ImageView image = dialog.findViewById(R.id.img);
        Button btn_full = dialog.findViewById(R.id.btnFull);
        Button btn_cerrar = dialog.findViewById(R.id.btnCerrar);

        String image_url_preview = mList.get(recyclerView.getChildAdapterPosition(view)).getImageUrlPreview();
        String image_url_large = mList.get(recyclerView.getChildAdapterPosition(view)).getLargeImageURL();
        String tags_api = mList.get(recyclerView.getChildAdapterPosition(view)).getTags();
        String user_api = mList.get(recyclerView.getChildAdapterPosition(view)).getUser();
        String type_api = mList.get(recyclerView.getChildAdapterPosition(view)).getType();
        int likes_api = mList.get(recyclerView.getChildAdapterPosition(view)).getLikes();

        Glide.with(this).load(image_url_large).into(image); //pinta la imagen
        txtTags.setText(tags_api);
        txtUser.setText(user_api);

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
                i.putExtra("image_url",image_url_large);
                i.putExtra("tags_api",tags_api);
                i.putExtra("user_api",user_api);
                i.putExtra("likes_api",likes_api);
                i.putExtra("type_api",type_api);
                startActivity(i);
            }
        });
        dialog.show();
    }
}

//singleton -> ahorro de bateria -> no cargar el dispositivo -> felicidad del usuario