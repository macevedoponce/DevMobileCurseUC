package com.acevedo.examen_sem3_apm;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class Login extends AppCompatActivity  {
    EditText edtUsuario, edtPassword, edtEmail;
    Button btnIniciar;
    public static final String ARCHIVO_PREF = "Preferencias_APM";
    //String sUsuario, sPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtCorreo);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pasando parametros al activity registrar
                //capturando datos
                String sUsuario = edtUsuario.getText().toString();
                String sPassword = edtPassword.getText().toString();
//        String sEmail = edtEmail.getText().toString();
                if("".equals(sUsuario)){
                    edtUsuario.setError("Campo requerido");
                    edtUsuario.requestFocus();
                }
                if("".equals(sPassword)){
                    edtPassword.setError("Campo requerido");
                    edtPassword.requestFocus();
                }
                if(sUsuario.equals("a@x.com") && sPassword.equals("123")){
                    //guardando preferencias de usuario
                    SharedPreferences preferences = getSharedPreferences(ARCHIVO_PREF,0); // 0 = Lectura y escritura
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("user",sUsuario);
                    editor.putString("password",sPassword);
                    editor.commit();
                    Toast.makeText(Login.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                    //fin de guardado de preferencias de usuario
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this, "Credenciales erradas", Toast.LENGTH_SHORT).show();
                }




                // mandando datos al mainActivity
//        i.putExtra("nombreUsuario",sUsuario);
//        i.putExtra("passUsuario",sPassword);
//        i.putExtra("emailUsuario",sEmail);
            }
        });
    }

}