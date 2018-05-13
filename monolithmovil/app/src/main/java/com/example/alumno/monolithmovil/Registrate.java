package com.example.alumno.monolithmovil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumno.monolithmovil.clases.Utilidades;

public class Registrate extends AppCompatActivity {

    EditText et_nombre, et_correo, et_edad, et_contraseña, et_pais, et_direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilidades.usuario="";
        setContentView(R.layout.activity_registrate);
        et_nombre = findViewById(R.id.name);
        et_correo = findViewById(R.id.correo);
        et_edad = findViewById(R.id.edad);
        et_contraseña = findViewById(R.id.contraseña);
        et_pais = findViewById(R.id.pais);
        et_direccion = findViewById(R.id.direccion);
        //String [] opciones = {"8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65"};
        //ArrayAdapter <String> miArray= new ArrayAdapter<>(this,R.layout.spinner_item_alex, opciones);
        //sp_edad.setAdapter(miArray);
    }

    // Metodo para el botón registrar

    public void Registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Proyecto", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // La abrimos para leerla (Modo lectura y escritura)

        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        String pais = et_pais.getText().toString();
        String direccion = et_direccion.getText().toString();

        if (!nombre.isEmpty() && !correo.isEmpty() && !pais.isEmpty() && ! direccion.isEmpty() && !contraseña.isEmpty() && !edad.isEmpty()){
                    ContentValues registro = new ContentValues();
                    Utilidades.usuario=nombre;
                    registro.put("nombre", nombre);
                    registro.put("edad",edad);
                    registro.put("pais", pais);
                    registro.put("direccion", direccion);
                    registro.put("correo", correo);
                    registro.put("contrasenia", contraseña);
                    BaseDeDatos.insert("personas", null, registro);
                    BaseDeDatos.close();
                    Toast.makeText(this, "Registro hecho exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuPrincipal.class);
                    startActivity(intent);
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }

    }

    // Metodo para el boton Regresar

    public  void Regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
