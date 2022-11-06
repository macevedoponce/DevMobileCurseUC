package com.example.sensors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import androidx.core.content.ContextCompat;

public class NivelPantalla extends View {
    int lado, radio, radioPeq, trazo;
    float[] angulos; // Recibirá las variaciones del sensor acelerómetro
    Bitmap fondo;  //  dibujo de fondo
    Paint trazoDibujo;
    Bitmap burbuja;

    public NivelPantalla(Context contexto, int lado){   // recibe el tamaño del espacio de dibujo con "lado"
        super(contexto);
        this.lado=lado;
        radio=lado/2;
        radioPeq=lado/10;//burbuja
        trazo=lado/100; // grosor del lapiz de dibujo
        angulos=new float[2]; //vectpr de 2 elementos iniciados en 0
        angulos[0]=0;
        angulos[1]=0;
        fondo=iniciaFondo();
        trazoDibujo=new Paint();
        trazoDibujo.setColor(Color.BLACK); //se pinta de negro
        trazoDibujo.setTextSize(20); //tamano de fuente 20

        BitmapDrawable bola=(BitmapDrawable) ContextCompat.getDrawable(contexto, R.drawable.burbuja);//aca podemos cambiar el drawable burbuja
        burbuja=bola.getBitmap();
        burbuja=Bitmap.createScaledBitmap(burbuja, radioPeq*2, radioPeq*2, true);

    }


    private Bitmap iniciaFondo(){
        Bitmap.Config conf=Bitmap.Config.ARGB_4444;//ARGB = canal Alpha -> Transparencia
        Bitmap fondo=Bitmap.createBitmap(lado, lado, conf);
        Canvas lienzo=new Canvas(fondo);
        Paint lapiz=new Paint();
        lapiz.setColor(Color.RED);
        lienzo.drawCircle(radio, radio, radio, lapiz);
        lapiz.setColor(Color.BLACK);
        lienzo.drawCircle(radio, radio, radio-trazo, lapiz); //crea un cisrculo más pequeño
        lapiz.setColor(Color.RED);
        lienzo.drawCircle(radio, radio, radioPeq+trazo, lapiz);
        lapiz.setStrokeWidth(trazo);
        lienzo.drawLine(radio, 0, radio, lado, lapiz);
        lienzo.drawLine(0, radio, lado, radio, lapiz);
        return fondo;

    }

    public void angulos(float[] angulos){
        this.angulos=angulos;
        invalidate();
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  // ajustamos dimensiones de la vista (no trabaja a pantalla completa)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(lado, lado); //esto se deberia dar el usuario

    }

    protected void onDraw(Canvas lienzo){
        super.onDraw(lienzo);
        lienzo.drawBitmap(fondo, 0, 0, null);
        int posX=radio-radioPeq+(int)(angulos[0]/10*radio);//(angulos[0]/10*radio -> transformacion de coordenada polar
        int posY=radio-radioPeq-(int)(angulos[1]/10*radio);
        lienzo.drawBitmap(burbuja, posX, posY, null);

    }

}
