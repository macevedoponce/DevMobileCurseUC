package com.example.mapsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsqlite1.Entidades.SqliteUtil;

import Utilidades.UtilAPM;

public class ConsultarUsuario extends AppCompatActivity {
EditText edtIdUsuario;
TextView txtNombre, txtTelefono;
Button btnBuscar;
//conectarnos a la base de datos
SqliteUtil conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        edtIdUsuario = findViewById(R.id.edtConsuIdUsuario);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnBuscar = findViewById(R.id.btnBuscar);
        //invocando al constructor
        conexion = new SqliteUtil(this, UtilAPM.BASE_DATOS, null, 1);
        // fin de inicializar el constructor
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarUsuario();
            }
        });
    }
    private void consultarUsuario(){
        SQLiteDatabase db = conexion.getReadableDatabase();//aceso a modo lectura
        // no mezclar codigo SQL con logia de negocio
        String[] parametros = {edtIdUsuario.getText().toString()}; // LO QUE LE MANDAS
        String[] lista_de_campos = {UtilAPM.CAMPO_ID,UtilAPM.CAMPO_NOMBRE, UtilAPM.CAMPO_TELEFONO};// LO QUE TRAERA SQL
        //cursos = almacenar los resultados de un query
        //captura en caso de error
        try{
            Cursor cursor = db.query(UtilAPM.TABLA_USUARIO, lista_de_campos, UtilAPM.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            txtNombre.setText(cursor.getString(1));
            txtTelefono.setText(cursor.getString(2));
            cursor.close();
        }catch(Exception e){
            Toast.makeText(this, "Error al buscar el IdUsuario "
                    + edtIdUsuario.getText().toString(), Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtTelefono.setText("");
        }

    }
}