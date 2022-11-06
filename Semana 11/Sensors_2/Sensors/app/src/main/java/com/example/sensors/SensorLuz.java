package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorLuz extends AppCompatActivity {
    TextView txtNivelLuz;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luz);
        txtNivelLuz = findViewById(R.id.txtNivelLuz);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(sensor == null){
            Toast.makeText(this, "no disponible", Toast.LENGTH_SHORT).show();
            finish();
        }

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                txtNivelLuz.setText(String.valueOf(sensorEvent.values[0]));
                int nivelgris = (int) sensorEvent.values[0];
                nivelgris = nivelgris*255/40000; //convirtiendo lo obtenido
                txtNivelLuz.setTextColor(Color.rgb(255-nivelgris,255-nivelgris,255-nivelgris));
                txtNivelLuz.setBackgroundColor(Color.rgb(nivelgris,nivelgris,nivelgris));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    @Override
    protected void onResume() {
        start();
        super.onResume();

    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();

    }
    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener,sensor);
    }

}