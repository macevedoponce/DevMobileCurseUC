package com.example.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class unCanvas extends View {
    Paint brocha = new Paint();
    Rect rectangulo = new Rect(50,50,500,500);

    public unCanvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas lienzo) {
        super.onDraw(lienzo);
        lienzo.drawRGB(200,200,200);
        brocha.setColor(Color.RED);
        lienzo.drawRect(rectangulo,brocha);
        brocha.setStyle(Paint.Style.FILL); //fill->rellenado
        brocha.setColor(Color.BLUE);
        lienzo.drawCircle(600,600,100,brocha);

    }
}
