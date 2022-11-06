package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MuestraLienzo extends AppCompatActivity {
    unCanvas unLienzo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_muestra_lienzo);
        unLienzo = new unCanvas(this);
        setContentView(unLienzo);
    }
}