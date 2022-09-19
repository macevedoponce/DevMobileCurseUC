package com.example.mapsqlite1.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utilidades.UtilAPM;
import Utilidades.UtilPelicula;

public class SqliteUtil extends SQLiteOpenHelper {
    //clase abstracta tiene algunos metodos que obligatoriamente deben de tener
    //constructor

    public SqliteUtil( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UtilPelicula.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pelicula");
    }


}
