package com.example.sensors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Acelerometro extends AppCompatActivity {
    TextView txtX, txtY, txtZ;
    SensorManager sensorManager; //recivirdatos del servicio
    Sensor sensor; //
    SensorEventListener sensorEventListener; //escuchador de sensores
    float rojo, verde, azul;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        player = MediaPlayer.create(this, R.raw.latigo);
        //primero conectarse a servicio
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor == null){
            Toast.makeText(this, "Acelerómetro no disponible", Toast.LENGTH_SHORT).show();
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSensorChanged(SensorEvent valorSensor) { //cambio en los datos del sensor
                float x = valorSensor.values[0];
                float y = valorSensor.values[1];
                float z = valorSensor.values[2];

                txtX.setText(String.valueOf(x));
                txtY.setText(String.valueOf(y));
                txtZ.setText(String.valueOf(z));

                rojo = (float) (x*255/(2*Math.PI));
                verde = (float) (y*255/(2*Math.PI));
                azul = (float) (z*255/(2*Math.PI));
                getWindow().getDecorView().setBackgroundColor(Color.rgb(rojo,verde,azul));

                if(azul>128){sonar();}


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }
    public void iniciar(){
        // enlazar el dispositivo con los datos que pides
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        // escuchador, que sensor quiers escuchar, tiempo de pedido de datos

    }
    public void stop(){
        // enlazar el dispositivo con los datos que pides
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }
    public void sonar(){
        player.setLooping(false);
        player.start();
    }
}