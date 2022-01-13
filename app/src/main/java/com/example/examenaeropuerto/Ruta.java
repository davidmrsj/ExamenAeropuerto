package com.example.examenaeropuerto;

import static com.example.examenaeropuerto.MainActivity.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Ruta extends AppCompatActivity {

    Spinner origen;
    Spinner destino;
    Button crearRuta;

    public static final String EXTRA_MESSAGE
            = "com.example.android.ExamenAeropuerto.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);
        origen = (Spinner) findViewById(R.id.spinner2);
        destino = (Spinner) findViewById(R.id.spinner3);
        crearRuta = (Button) findViewById(R.id.button4);

        rellenarSpinner();
        crearRutaMaps();
    }

    public void rellenarSpinner(){
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT nombre FROM aeropuerto;", null);
        while(c.moveToNext()){
            lista.add(c.getString(0));
        }
        adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        origen.setAdapter(adaptador);
        destino.setAdapter(adaptador);
    }

    public void crearRutaMaps(){
        crearRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                String[] origenDestino= new String[2];
                origenDestino[0] = origen.getSelectedItem().toString();
                origenDestino[1] = destino.getSelectedItem().toString();
                intent.putExtra(EXTRA_MESSAGE, origenDestino);
                startActivity(intent);
            }
        });
    }
}