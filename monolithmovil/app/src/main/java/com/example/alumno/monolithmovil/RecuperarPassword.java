package com.example.alumno.monolithmovil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RecuperarPassword extends AppCompatActivity {

    EditText et_destino;

    String CDe="hawkward.ipn@gmail.com", CPara ="", CAsunto="Recuperación de la cuenta",CMensaje=""; // Contraseña por si hace falta TheH4wK_f|1|7*5 o TheH4wK_fl1l7*5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        et_destino = findViewById(R.id.correo);
    }

    // Metodo para el boton regresar
    public void Regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Metodo para el botón enviar
    public void Enviar(View Envia) {
        CPara = et_destino.getText().toString();
        if(!CPara.isEmpty()){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Proyecto", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
            Cursor fila = BaseDeDatos.rawQuery("select nombre, contrasenia from personas where correo ='" + CPara + "';", null);
            if(fila.moveToFirst()){
                String nombre = fila.getString(0);
                String contraseña = fila.getString(1);
                CMensaje = "Tu usuario es: " +nombre + "\n" + "Tu contraseña es: " + contraseña;
                String de[] = {CDe};
                String[] para = {CPara};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, de);
                emailIntent.putExtra(Intent.EXTRA_CC, para);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, CAsunto); // Asunto
                emailIntent.putExtra(Intent.EXTRA_TEXT, CMensaje); // Mensaje
                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                    finish(); // Tal vez debería de crear un intent que me regresa al MainActivity. En caso de que me regrese a la pagina de enviar correo.
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "No hemos encontrado ningun correo como el que has escrito", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
                fila.close();
            }
        }else{
            Toast.makeText(this, "Debes ingresar un correo electrónico", Toast.LENGTH_SHORT).show();
        }

    }
}
