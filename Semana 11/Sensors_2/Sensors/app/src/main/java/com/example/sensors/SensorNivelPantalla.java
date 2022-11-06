package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

public class SensorNivelPantalla extends AppCompatActivity implements SensorEventListener {
NivelPantalla nivel;
SensorManager sensorManager;
Sensor sensor;
MediaPlayer player;
float centro, borde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sensor_nivel_pantalla);
        //lado

        nivel = new NivelPantalla(this,600);
        setContentView(nivel);
        player = MediaPlayer.create(this, R.raw.latigo);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        nivel.angulos(sensorEvent.values); //cada vez que se tenga un cambio en el sensor, se pase los eventos a nivel, angulos
         int x = (int) nivel.posX;
         int y = (int) nivel.posY;

         if(x == nivel.radio-nivel.radioPeq && y == nivel.radio-nivel.radioPeq){sonar();}
        Toast.makeText(this, x + "," + y, Toast.LENGTH_SHORT).show();

    }

    private void sonar() {
        player.setLooping(false);
        player.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

//cuando burbuja toque un borde reproduzcan un sonido de igual manera cuando esta al centro
//agregarle algo como cambio de color de la burbuja, etc