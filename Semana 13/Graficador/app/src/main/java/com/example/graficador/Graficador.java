package com.example.graficador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class Graficador extends View {

    private Path drawPath;
    private boolean erase = false;
    private Paint drawPaint, canvasPaint;
    private Canvas drawCanvas;
    private int paintColor = 0xFF660000;
    private Bitmap canvasBitmap;
    private float brushSize,lastBrushSize;

    float touchX=300,touchY=300;
    float media = 100;



    public Graficador(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

        drawCanvas.drawColor(Color.WHITE);
        //invalidate();
    }
    public void setErase(boolean isErase){
        erase = isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);
    }
    public void setBrushSize(float newSize){
        float pixelAmout = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize, getResources().getDisplayMetrics());
        brushSize = pixelAmout;
        drawPaint.setStrokeWidth(brushSize);
    }
    public void setLastBrushSize(float lastSize){
        lastBrushSize = lastSize;
    }
    public float getBrushSize(){
        return lastBrushSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath,drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        int act = event.getAction();
        switch (act){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath,drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    public void setColor(int newColor){
        invalidate();
        paintColor = newColor;
        drawPaint.setColor(paintColor);

    }
    public void setupDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        brushSize = 10;
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);

    }

    public void rectangulo(){
        Path rectangulo = new Path();
        rectangulo.addRect(touchX,touchY,500,500,Path.Direction.CCW);
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(brushSize);
        paint.setStyle(Paint.Style.STROKE);
        drawCanvas.drawPath(rectangulo,paint);
    }

    public void circulo(){
        Path circulo = new Path();
        circulo.addCircle(touchX,touchY,200,Path.Direction.CCW);
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(brushSize);
        paint.setStyle(Paint.Style.STROKE);
        drawCanvas.drawPath(circulo,paint);

    }

    public void triangulo(){
        Path triangulo = new Path();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); // rellenado
        paint.setColor(paintColor);
        triangulo.moveTo(touchX,touchY);//punto inicial
        triangulo.lineTo(touchX+touchY, 200); // dibuja una linea hasta la coordenada indicada
        triangulo.lineTo(touchX-touchY, 200); // dibuja una linea hasta la coordenada indicada
        triangulo.close();//cerrar hasta el punto incial
        drawCanvas.drawPath(triangulo,paint);
    }

}

/*

crear un paint
con un menu legir el color
grosor de linea
dibujar circulo
dibujar rectangulo
guardar como imagen 17
abrir la imagen 20

*/