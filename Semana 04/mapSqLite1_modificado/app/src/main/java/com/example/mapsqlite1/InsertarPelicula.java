package com.example.mapsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;

import Utilidades.UtilPelicula;

public class InsertarPelicula extends AppCompatActivity {
    EditText edtId, edtNombre, edtDescripcion, edtYear, edtTime, edtgenero;
    Button btnRegistrarPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_pelicula);
        edtId = findViewById(R.id.edtIdPelicula);
        edtNombre = findViewById(R.id.edtNombrePelicula);
        edtDescripcion = findViewById(R.id.edtDescripcionPelicula);
        edtYear = findViewById(R.id.edtAnioPelicula);
        edtTime = findViewById(R.id.edtDuracionPelicula);
        edtgenero = findViewById(R.id.edtGeneroPelicula);
        btnRegistrarPelicula = findViewById(R.id.btnRegistrarPelicula);

        btnRegistrarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regPelicula();
            }
        });


    }
    private void regPelicula(){
        SqliteUtil conexion = new SqliteUtil(this,"bd_pelicula",null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(UtilPelicula.CAMPO_ID, edtId.getText().toString());
        valores.put(UtilPelicula.CAMPO_NOMBRE, edtNombre.getText().toString());
        valores.put(UtilPelicula.CAMPO_DESCRIPCION, edtDescripcion.getText().toString());
        valores.put(UtilPelicula.CAMPO_YEAR, edtYear.getText().toString());
        valores.put(UtilPelicula.CAMPO_DURACION, edtTime.getText().toString());
        valores.put(UtilPelicula.CAMPO_GENERO, edtgenero.getText().toString());
        Long resultado = db.insert(UtilPelicula.TABLA_USUARIO,null,valores);
        Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT).show();
        db.close();
    }
}