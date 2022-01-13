package com.example.examenaeropuerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = openOrCreateDatabase("Aeropuertos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS aeropuerto(nombre varchar primary key, coordenadas varchar, imagen blob)");

    }

    public void lanzarRegistro(View view){
        Intent intent = new Intent(getApplicationContext(), Registro.class);
        startActivity(intent);
    }

    public void eliminarRegistro(View view){
        Intent intent = new Intent(getApplicationContext(), eliminar.class);
        startActivity(intent);
    }

    public void lanzarRuta(View view){
        Intent intent = new Intent(getApplicationContext(), Ruta.class);
        startActivity(intent);
    }
}