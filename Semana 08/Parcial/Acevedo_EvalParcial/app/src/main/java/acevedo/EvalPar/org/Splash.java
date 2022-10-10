package acevedo.EvalPar.org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    TextView tvCreador;
    ImageView imgLogo, imgApellido;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        tvCreador= findViewById(R.id.tvCreador);
        imgLogo= findViewById(R.id.imgLogo);
        imgApellido= findViewById(R.id.imgApellido);
        progressBar=findViewById(R.id.progressBar);

        tvCreador.setAnimation(animacion2);
        imgLogo.setAnimation(animacion1);
        imgApellido.setAnimation(animacion1);
        progressBar.setAnimation(animacion2);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        },3000);
    }
}