package com.acevedo.sem2_maap_appbanco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transferencia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_registrar:
            {
                Toast.makeText(this, "Registrar usuario", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_transferencia:
            {
                Toast.makeText(this, "Efectuar Transferencia", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_consultartransferencia:
            {
                Toast.makeText(this, "Consultar Transferencia", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_preferencias:
            {
                Toast.makeText(this, "Configurar Preferencias", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}