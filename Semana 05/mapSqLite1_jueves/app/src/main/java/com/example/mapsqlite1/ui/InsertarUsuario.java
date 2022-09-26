package com.example.mapsqlite1.ui;

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

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.R;

import Utilidades.UtilAPM;


public class InsertarUsuario extends Fragment {
    EditText edtNameUser, edtPhoneUser, edtIdUSer;
    Button btnRegistrarUsuario;

    public InsertarUsuario() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_insertar_usuario, container, false);
        edtIdUSer = vista.findViewById(R.id.edtIdUser);
        edtNameUser = vista.findViewById(R.id.edtNameUser);
        edtPhoneUser = vista.findViewById(R.id.edtPhoneUser);
        btnRegistrarUsuario = vista.findViewById(R.id.btnRegistrarUsuario);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUsuario();
            }
        });

        return vista;
    }

    public void regUsuario(){
        SqliteUtil conexion = new SqliteUtil(getContext(),"bd_usuario",null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(UtilAPM.CAMPO_ID, edtIdUSer.getText().toString());
        valores.put(UtilAPM.CAMPO_NOMBRE, edtNameUser.getText().toString());
        valores.put(UtilAPM.CAMPO_TELEFONO, edtPhoneUser.getText().toString());
        Long resultado = db.insert(UtilAPM.TABLA_USUARIO,null,valores);
        Toast.makeText(getContext(), "Registro completo", Toast.LENGTH_SHORT).show();
        db.close();
    }
}