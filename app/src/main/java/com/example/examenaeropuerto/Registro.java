package com.example.examenaeropuerto;

import static com.example.examenaeropuerto.MainActivity.db;

import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Registro extends AppCompatActivity {
    Bitmap imagen;
    private EditText txtImagen;
    EditText txtNombre;
    EditText txtCoordenadas;
    Button registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtImagen = (EditText) findViewById(R.id.txtUrl);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtCoordenadas = (EditText) findViewById(R.id.txtCoordenadas);
        registro = (Button) findViewById(R.id.btnRegistro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAero();
            }
        });

    }



    public Bitmap obtenerImagen(String url){
        Bitmap bm = null;
        InputStream inputStream = null;
        BufferedInputStream buffer = null;
        try{
            URLConnection conexion = new URL(url).openConnection();
            conexion.connect();
            inputStream = conexion.getInputStream();
            buffer = new BufferedInputStream(inputStream);
            bm = BitmapFactory.decodeStream(buffer);
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    public void registrarAero(){
        imagen = obtenerImagen(txtImagen.getText().toString());
        String nombre = txtNombre.getText().toString();
        String coordenadas = txtCoordenadas.getText().toString();
        try {
            db.execSQL("INSERT INTO aeropuerto VALUES('" + nombre + "', '" + coordenadas + "', " + R.drawable.ic_launcher_background + ")");
            Toast.makeText(getApplicationContext(), "Aeropuerto registrado", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), "Error al registrar aeropuerto", Toast.LENGTH_LONG).show();
        }
    }
}
