package com.example.alumno.monolithmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listaNotas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        ListView lista;
        ArrayAdapter<String> adaptador;

        lista = (ListView)findViewById(R.id.listView);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        lista.setAdapter(adaptador);
    }
}
