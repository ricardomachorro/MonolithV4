package com.example.alumno.monolithmovil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alumno.monolithmovil.ActividadesCartas;
import com.example.alumno.monolithmovil.R;

import java.util.ArrayList;

public class AdapCartasFin extends BaseAdapter {

    private static LayoutInflater infleter=null;

    Context contexto;
    ArrayList<ActividadesCartas> lista=new ArrayList<ActividadesCartas> (  );


    public AdapCartasFin(Context contexto, ArrayList<ActividadesCartas> lista) {
        this.contexto = contexto;
        this.lista = lista;
        infleter=(LayoutInflater) contexto.getSystemService ( contexto.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return lista.size ();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View Vista=infleter.inflate ( R.layout.carta_act_finalizada,null);
        TextView titulo=Vista.findViewById ( R.id.ActFinTitulo );
        TextView Cateogoria=Vista.findViewById ( R.id.ActFinCat );
        TextView Fecha=Vista.findViewById ( R.id.ActFinFecha );
        titulo.setText(lista.get ( position).getNombreAct ());
        Cateogoria.setText(lista.get ( position ).getCategoria ());
         Fecha.setText ( lista.get(position).getFecha () );
        ImageButton btnEliminar=Vista.findViewById ( R.id.btnDrop  );
        ImageButton btnCambiar=Vista.findViewById ( R.id.btnChange );
        CheckBox check=Vista.findViewById ( R.id.checkAct );

        return Vista;
    }

    public void NotifyChange() {
        notifyDataSetChanged();
    }
}
