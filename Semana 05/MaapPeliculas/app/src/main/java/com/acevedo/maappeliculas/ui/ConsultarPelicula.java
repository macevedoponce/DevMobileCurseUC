package com.acevedo.maappeliculas.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.maappeliculas.Entidades.SqliteUtil;
import com.acevedo.maappeliculas.R;
import com.acevedo.maappeliculas.Utilidades.UtilAPM;


public class ConsultarPelicula extends Fragment {
    EditText edtIdPelicula;
    TextView txtTitle, txtDescription, txtDuration, txtYear, txtDirector, txtCategory;
    Button btnBuscar;
    SqliteUtil conexion;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_consultar_pelicula, container, false);
        edtIdPelicula = vista.findViewById(R.id.edtConsuIdPelicula);
        txtTitle = vista.findViewById(R.id.txtTitle);
        txtDescription = vista.findViewById(R.id.txtDescription);
        txtDuration = vista.findViewById(R.id.txtDuration);
        txtYear = vista.findViewById(R.id.txtYear);
        txtDirector = vista.findViewById(R.id.txtDirector);
        txtCategory = vista.findViewById(R.id.txtCategory);
        btnBuscar = vista.findViewById(R.id.btnBuscarPelicula);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarPelicula();
            }
        });
        return vista;
    }

    private void consultarPelicula() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {
                edtIdPelicula.getText().toString()
        };
        String[] lista_de_campos = {
                UtilAPM.CAMPO_ID,
                UtilAPM.CAMPO_DESCRIPTION,
                UtilAPM.CAMPO_DURATION,
                UtilAPM.CAMPO_YEAR,
                UtilAPM.CAMPO_DIRECTOR,
                UtilAPM.CAMPO_CATEGORY
        };
        try{
            Cursor cursor = db.query(UtilAPM.TABLA_PELICULA, lista_de_campos,UtilAPM.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            txtTitle.setText(cursor.getString(1));
            txtDescription.setText(cursor.getString(2));
            txtDuration.setText(cursor.getString(3));
            txtYear.setText(cursor.getString(4));
            txtDirector.setText(cursor.getString(5));
            txtCategory.setText(cursor.getString(6));

        }catch (Exception e){
            Toast.makeText(getContext(), "Error al buscar la Pelicula "
                    + edtIdPelicula.getText().toString(), Toast.LENGTH_SHORT).show();
            txtTitle.setText("");
            txtDescription.setText("");
            txtDuration.setText("");
            txtYear.setText("");
            txtDirector.setText("");
            txtCategory.setText("");

        }
    }
}