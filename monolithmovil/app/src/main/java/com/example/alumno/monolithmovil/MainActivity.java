package com.example.alumno.monolithmovil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre, et_contraseña;
    Button xd ;
 //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nombre = findViewById(R.id.name);
        et_contraseña = findViewById(R.id.contrasenia);
        xd = findViewById(R.id.XD);
        xd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registrate.class);
                startActivity(intent);
            }
        }
        );
    }

    // Metodo para el botón Ingresar

    public void Ingresar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Proyecto", null, 1);
        SQLiteDatabase DataBase = admin.getReadableDatabase();

        String nombre = et_nombre.getText().toString();
        String password = et_contraseña.getText().toString();

        if (!nombre.isEmpty() && !password.isEmpty()){
            Cursor fila = DataBase.rawQuery("select contrasenia from personas where nombre = '" + nombre +"'", null);
            if (fila.moveToFirst()){
                String contraseña = fila.getString(0);
                if (password.equals(contraseña)){
                    Intent intent = new Intent(this, MenuPrincipal.class);
                    startActivity(intent);
                    DataBase.close();
                    fila.close();
                }else{
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    DataBase.close();
                    fila.close();
                }
            }else{
                Toast.makeText(this, "No estas registrado o el nombre de usuario es incorrecto", Toast.LENGTH_SHORT).show();
                DataBase.close();
                fila.close();
            }
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            DataBase.close();
        }
    }

    // Metodo de prueba
    public void IngresaPrueba (View view){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    // Metodo para el boton regístrate
    //public void Registrar1 (View view){
    //    Intent intent = new Intent(this, Registrate.class);
    //    startActivity(intent);
    //}
}
