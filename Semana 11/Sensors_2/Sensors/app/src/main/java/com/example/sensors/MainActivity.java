package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtListado;
    List<Sensor> listaSensores;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtListado = findViewById(R.id.txtListado);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //(sensorManager) -> adapta a la clase SensorManager | cast
        // se prepara para recepcionar los servicios del sistema operativo
        //el software no puede acceder directamente al hardware
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor: listaSensores){
            txtListado.setText(txtListado.getText() + " \n" + sensor.getName() + "> " + sensor.getVendor());
        }

    }
}