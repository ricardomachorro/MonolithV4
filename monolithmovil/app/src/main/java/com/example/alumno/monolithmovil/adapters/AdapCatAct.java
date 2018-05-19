package com.example.alumno.monolithmovil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.monolithmovil.R;

import java.util.ArrayList;

public class AdapCatAct extends BaseAdapter {

    private static LayoutInflater infleter=null;

    Context contexto;
    ArrayList<CategoriasCartasAct> lista= new ArrayList<CategoriasCartasAct> (  );

    public AdapCatAct(Context contexto, ArrayList<CategoriasCartasAct> lista) {
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

        final View Vista=infleter.inflate ( R.layout.carta_categoria,null);
        TextView titulo=Vista.findViewById ( R.id.ActFinTitulo );
        ImageView img= Vista.findViewById (R.id.imagenCategoriaAct);
        titulo.setText ( lista.get ( position ).getNombre () );
        return Vista;
    }

    public void NotifyChange() {

        notifyDataSetChanged();
    }
}
