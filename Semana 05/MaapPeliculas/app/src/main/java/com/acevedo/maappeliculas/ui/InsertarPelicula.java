package com.acevedo.maappeliculas.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acevedo.maappeliculas.Entidades.SqliteUtil;
import com.acevedo.maappeliculas.R;
import com.acevedo.maappeliculas.Utilidades.UtilAPM;


public class InsertarPelicula extends Fragment {
EditText edtId, edtTitle, edtDescription, edtDuration, edtYear, edtDirector, edtCategory;
Button btnRegistrarPelicula;


    public InsertarPelicula() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_insertar_pelicula, container, false);
        edtId = vista.findViewById(R.id.edtIdPelicula);
        edtTitle = vista.findViewById(R.id.edtTitlePelicula);
        edtDescription = vista.findViewById(R.id.edtDescriptionPelicula);
        edtDuration = vista.findViewById(R.id.edtDurationPelicula);
        edtYear = vista.findViewById(R.id.edtYearPelicula);
        edtDirector = vista.findViewById(R.id.edtDirectorPelicula);
        edtCategory = vista.findViewById(R.id.edtCategoryPelicula);
        btnRegistrarPelicula = vista.findViewById(R.id.btnInsertarPelicula);
        btnRegistrarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regPelicula();
            }
        });
        return vista;
    }
    public void regPelicula(){
        SqliteUtil conexion = new SqliteUtil(getContext(),"bd_pelicula",null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(UtilAPM.CAMPO_ID,edtId.getText().toString());
        valores.put(UtilAPM.CAMPO_TITLE,edtTitle.getText().toString());
        valores.put(UtilAPM.CAMPO_DESCRIPTION,edtDescription.getText().toString());
        valores.put(UtilAPM.CAMPO_DURATION,edtDuration.getText().toString());
        valores.put(UtilAPM.CAMPO_YEAR,edtYear.getText().toString());
        valores.put(UtilAPM.CAMPO_DIRECTOR,edtDirector.getText().toString());
        valores.put(UtilAPM.CAMPO_CATEGORY,edtCategory.getText().toString());
        Long resultado = db.insert(UtilAPM.TABLA_PELICULA,null,valores);
        Toast.makeText(getContext(), "Pelicula Registrada correctamente!", Toast.LENGTH_SHORT).show();
        db.close();
    }
}