package com.example.alumno.monolithmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActividadCambio extends AppCompatActivity {

    TextView TituloActividad,TituloDatos,TituloFormulario,txtNombre,txtFecha,txtCategoria;
    EditText txtNombreNuevo,txtFechaNueva,txtCategoriaNueva;
    Button btnCambio;
    String NombreAct,FechaAct,CatAct,IDActividad,NombreUsuario,IDUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_actividad_cambio );
        TituloActividad=findViewById ( R.id.TituloCambioActividad );
        TituloDatos=findViewById ( R.id.txtDatos );
        TituloFormulario=findViewById ( R.id.txtFormulario );
        txtNombre=findViewById ( R.id.txtNombreAntiguo );
        txtFecha=findViewById ( R.id.txtFechaAntigua );
        txtCategoria=findViewById ( R.id.txtCategoriaAntigua );
        txtNombreNuevo=findViewById ( R.id.txtNuevoNombre );
        txtFechaNueva=findViewById ( R.id.txtNuevaFecha );
        txtCategoriaNueva=findViewById ( R.id.txtNuevaCategoria );
        btnCambio=findViewById ( R.id.btnCambio );
        Intent intent=getIntent ();
        NombreAct=intent.getStringExtra ( "NombreActividad" );
        FechaAct=intent.getStringExtra ( "FechaActividad" );
        CatAct=intent.getStringExtra ( "CategoriaActividad" );
        IDActividad=intent.getStringExtra ( "IDActividad" );
        NombreUsuario=intent.getStringExtra ( "NombreUsuario" );
        IDUsuario=intent.getStringExtra ( "IDUsuario" );
        txtNombre.setText ( NombreAct );
        txtFecha.setText ( FechaAct );
        txtCategoria.setText ( CatAct );



    }
}
