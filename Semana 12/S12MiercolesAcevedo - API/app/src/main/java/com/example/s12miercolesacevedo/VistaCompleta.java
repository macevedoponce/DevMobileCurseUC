package com.example.s12miercolesacevedo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class VistaCompleta extends AppCompatActivity {
    TextView txtTags, txtUser, txtType, txtLikes;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_completa);

        imageView = findViewById(R.id.img_full);
        txtTags = findViewById(R.id.txtTags);
        txtUser = findViewById(R.id.txtUser);
        txtType = findViewById(R.id.txtType);
        txtLikes = findViewById(R.id.txtLikes);

        String image_url =getIntent().getExtras().getString("image_url");
        String tags_api = getIntent().getExtras().getString("tags_api");
        String user_api = getIntent().getExtras().getString("user_api");
        Integer likes_api = getIntent().getExtras().getInt("likes_api",1);
        String type_api = getIntent().getExtras().getString("type_api");

        Glide.with(this).load(image_url).into(imageView); //pinta la imagen
        txtTags.setText(tags_api);
        txtLikes.setText(likes_api.toString());
        txtType.setText(type_api);
        txtUser.setText(user_api);
    }
}