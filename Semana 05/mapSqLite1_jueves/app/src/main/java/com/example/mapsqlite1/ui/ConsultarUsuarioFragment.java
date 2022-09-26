package com.example.mapsqlite1.ui;

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

import com.example.mapsqlite1.Entidades.SqliteUtil;
import com.example.mapsqlite1.R;

import Utilidades.UtilAPM;

public class ConsultarUsuarioFragment extends Fragment {

    EditText edtIdUsuario;
    TextView txtNombre, txtTelefono;
    Button btnBuscar;
    //conectarnos a la base de datos
    SqliteUtil conexion;


    public ConsultarUsuarioFragment() {
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
        View vista = inflater.inflate(R.layout.fragment_consultar_usuario, container, false);
        edtIdUsuario = vista.findViewById(R.id.edtConsuIdUsuario);
        txtNombre = vista.findViewById(R.id.txtNombre);
        txtTelefono = vista.findViewById(R.id.txtTelefono);
        btnBuscar = vista.findViewById(R.id.btnBuscar);
        //invocando al constructor
        conexion = new SqliteUtil(getContext(), UtilAPM.BASE_DATOS, null, 1);
        // fin de inicializar el constructor
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarUsuario();
            }
        });
        return vista;
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
            Toast.makeText(getContext(), "Error al buscar el IdUsuario "
                    + edtIdUsuario.getText().toString(), Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtTelefono.setText("");
        }

    }
}