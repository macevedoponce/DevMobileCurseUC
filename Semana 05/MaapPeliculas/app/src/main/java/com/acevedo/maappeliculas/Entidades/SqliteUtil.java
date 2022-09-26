package com.acevedo.maappeliculas.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acevedo.maappeliculas.Utilidades.UtilAPM;

public class SqliteUtil extends SQLiteOpenHelper {
    public SqliteUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(UtilAPM.CREAR_TABLA_PELICULA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pelicula");
    }
}
