package com.example.mapsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;

import Utilidades.UtilAPM;


public class InsertarUsuario extends AppCompatActivity {
    EditText edtNameUser, edtPhoneUser, edtIdUSer;
    Button btnRegistrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario);
        edtIdUSer = findViewById(R.id.edtIdUser);
        edtNameUser = findViewById(R.id.edtNameUser);
        edtPhoneUser = findViewById(R.id.edtPhoneUser);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUsuario();
            }
        });

  }
  public void regUsuario(){
        SqliteUtil conexion = new SqliteUtil(this,"bd_usuario",null,1);
      SQLiteDatabase db = conexion.getWritableDatabase();
      ContentValues valores = new ContentValues();
      valores.put(UtilAPM.CAMPO_ID, edtIdUSer.getText().toString());
      valores.put(UtilAPM.CAMPO_NOMBRE, edtNameUser.getText().toString());
      valores.put(UtilAPM.CAMPO_TELEFONO, edtPhoneUser.getText().toString());
      Long resultado = db.insert(UtilAPM.TABLA_USUARIO,null,valores);
      Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT).show();
      db.close();
  }
}