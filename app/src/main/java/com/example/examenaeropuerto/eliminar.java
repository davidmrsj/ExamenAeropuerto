package com.example.examenaeropuerto;

import static com.example.examenaeropuerto.MainActivity.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class eliminar extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        sp = (Spinner) findViewById(R.id.spinner);
        rellenarSpinner();
    }

    public void rellenarSpinner(){
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT nombre FROM aeropuerto;", null);
        while(c.moveToNext()){
            lista.add(c.getString(0));
        }
        adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        sp.setAdapter(adaptador);
    }

    public void eliminarAeropuerto(View view){
        String aeropuertoAEliminar = (String)sp.getSelectedItem();
        try {
            db.execSQL("DELETE FROM aeropuerto WHERE nombre='" + aeropuertoAEliminar + "';");
            Toast.makeText(getApplicationContext(), "Aeropuerto eliminado", Toast.LENGTH_LONG).show();
            rellenarSpinner();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Aeropuerto no eliminado", Toast.LENGTH_LONG).show();
        }
    }

}