package com.acevedo.s12acevedo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class VistaCompleta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_completa);

        ImageView imageView = findViewById(R.id.img_full);
        String image_url =getIntent().getExtras().getString("image_url");
        Glide.with(this).load(image_url).into(imageView); //pinta la imagen
    }
}