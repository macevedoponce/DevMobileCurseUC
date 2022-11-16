package com.example.graficador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {


    Graficador drawView;
    ImageButton imgNew,imgEraser,imgColor,imgSave,imgUpload,imgPencil,imgCiculo,imgTriangulo,imgCuadrado;
    SeekBar seekBar;
    TextView txtPenSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = findViewById(R.id.drawing);
        imgNew = findViewById(R.id.btnNew);
        imgEraser = findViewById(R.id.btnEraser);
        imgColor = findViewById(R.id.btnColor);
        imgSave = findViewById(R.id.btnSave);
        imgUpload = findViewById(R.id.btnUpload);
        imgPencil = findViewById(R.id.btnLinea);
        imgCiculo = findViewById(R.id.btnCirculo);
        imgTriangulo = findViewById(R.id.btnTriangulo);
        imgCuadrado = findViewById(R.id.btnCuadrado);
        seekBar = findViewById(R.id.penSize);
        txtPenSize = findViewById(R.id.txtPenSize);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText(i + "dp");
                drawView.setBrushSize(i);
                seekBar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });
        imgEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setErase(true);
                drawView.setBrushSize(drawView.getBrushSize());
            }
        });
        imgPencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setErase(false);
                drawView.setBrushSize(drawView.getBrushSize());
            }
        });
        imgNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogo();

            }
        });
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDibujo();
            }
        });
        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione APP"),10);
            }
        });
        imgCuadrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.rectangulo();
            }
        });
        imgCiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.circulo();
            }
        });
        imgTriangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.triangulo();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            Uri path = data.getData();

            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Drawable drawable = Drawable.createFromStream(inputStream, path.toString());

            drawView.setBackground(drawable);
        }
    }

    private void guardarDibujo() {
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Guardar Archivo");
        saveDialog.setMessage("guardar dibujo en el dispositivo?");
        saveDialog.setPositiveButton("Si",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                drawView.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(),drawView.getDrawingCache(), UUID.randomUUID().toString()+".png","Dibujo Guardado");
                if(imgSaved != null){
                    Toast.makeText(MainActivity.this, "Imagen guardada en galeria", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "No guardado", Toast.LENGTH_SHORT).show();
                }
                drawView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                drawView.startNew();
                dialogInterface.cancel();
            }
        });
        saveDialog.show();
    }

    private void showDialogo() {
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("Nuevo Canvas");
        newDialog.setMessage("Crear nuevo canvas");
        newDialog.setPositiveButton("Si",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                drawView.startNew();
                dialogInterface.dismiss();
            }
        });
        newDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        newDialog.show();
    }

    private void openColorPicker() {
        int colorDef=200;
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, colorDef, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                drawView.setColor(color);
                //graficador.setPenColor(color);
            }
        });
        ambilWarnaDialog.show();
    }
}