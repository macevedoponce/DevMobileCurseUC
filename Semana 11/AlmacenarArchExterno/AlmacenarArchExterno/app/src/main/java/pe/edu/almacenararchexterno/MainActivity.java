package pe.edu.almacenararchexterno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.jar.JarEntry;

public class MainActivity extends AppCompatActivity {
    EditText txtcontenido;
    Button btnguardar, btnleer, btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtcontenido= findViewById(R.id.txtcontenido);
        btnguardar= findViewById(R.id.btnGuardar);
        btnleer=findViewById(R.id.btnLeer);
        btnsalir=findViewById(R.id.btnSalir);
    }

    public void guardar(View v){
        String estado= Environment.getExternalStorageState();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            try{
                String texto=txtcontenido.getText().toString();
                FileOutputStream objEscribirArchivo = null;
                objEscribirArchivo=openFileOutput("MiArchivo.txt", MODE_PRIVATE);
                objEscribirArchivo.write(texto.getBytes());
                objEscribirArchivo.close();
                Toast.makeText(this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
                txtcontenido.setText("");
            }
            catch (Exception e){
                Toast.makeText(this, "Hubo error en la escritura del archivo", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void leer(View v){
        Toast.makeText(this, "Lectura", Toast.LENGTH_SHORT).show();
        try{
            BufferedReader leerArchivo= new BufferedReader(new InputStreamReader(openFileInput("MiArchivo.txt")));
            String texto= leerArchivo.readLine();
            leerArchivo.close();
            txtcontenido.setText(texto);
        }
        catch(Exception e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();

        }
    }

}
