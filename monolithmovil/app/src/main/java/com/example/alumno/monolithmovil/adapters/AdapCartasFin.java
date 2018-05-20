package com.example.alumno.monolithmovil.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumno.monolithmovil.ActividadesCartas;
import com.example.alumno.monolithmovil.Actividades_NoTerminadas;
import com.example.alumno.monolithmovil.MainActivity;
import com.example.alumno.monolithmovil.R;
import com.example.alumno.monolithmovil.Sesion;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class AdapCartasFin extends BaseAdapter {

    private static LayoutInflater infleter=null;
    String IP = MainActivity.IP;
    String PUERTO = MainActivity.PUERTO;
   String resultado;
   Sesion sesion;
   View Vista;


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

        Vista=infleter.inflate ( R.layout.carta_act_finalizada,null);
        sesion= sesion=new Sesion (Vista.getContext ());
        TextView titulo=Vista.findViewById ( R.id.ActFinTitulo );
        TextView Cateogoria=Vista.findViewById ( R.id.ActFinCat );
        TextView Fecha=Vista.findViewById ( R.id.ActFinFecha );
        titulo.setText(lista.get ( position).getNombreAct ());
        Cateogoria.setText(lista.get ( position ).getCategoria ());
         Fecha.setText ( lista.get(position).getFecha () );
        ImageButton btnEliminar=Vista.findViewById ( R.id.btnDrop  );
        btnEliminar.setTag (lista.get ( position ).getIdentificador () );
        ImageButton btnCambiar=Vista.findViewById ( R.id.btnChange );
        CheckBox check=Vista.findViewById ( R.id.checkAct );
        check.setTag(lista.get ( position ).getIdentificador () );
        if(lista.get ( position ).getEstado ().equals ( "true" )){
            check.setChecked ( true );
        }
        check.setOnClickListener (  new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                    new ChecarAct (  ).execute (v.getTag ().toString (),sesion.getNombreUsuario () );

            }
        } );

        btnEliminar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                new EliminarAct ().execute ( v.getTag ().toString () );
            }
        } );


        return Vista;
    }

    public void NotifyChange() {

        notifyDataSetChanged();
    }


    public class ChecarAct extends AsyncTask<String, String, Boolean> {



        @Override
        protected Boolean doInBackground(String... params) {


            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/ActividadWebMethods?WSDL";
            String METHOD_NAME = "ChecarActividad";
            String SOAP_ACTION = "http://WebService/ChecarActividad";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("IDActividad", params[0]); // para agregar parámetros a nuestro método de nuestro webservice
            request.addProperty("Usuario", params[1]);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope( SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            envelope.dotNet = false;

            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.debug = true; // para las pruebas


            try{
                ht.call(SOAP_ACTION, envelope);
                String a = ht.requestDump;
                String b = ht.responseDump;
                Log.println(Log.INFO, "request", a);
                Log.println(Log.INFO, "response", b);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                resultado = response.toString(); // con esta variable guardamos lo que nos returne el web service
                Log.i("Respuesta" ,  resultado);
                return true; // este debería ir dentro del try
            }
            catch(Exception error){
                error.printStackTrace();
                return false;
            }



        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if(success){ // para saber que se ejecutó correctamente mi web service (success)
                if(resultado.equals("Error")){
                    Toast.makeText (Vista.getContext (),"Error ", Toast.LENGTH_SHORT ).show ();
                }else{
                    Toast.makeText (Vista.getContext (),"Checheo Exitoso ", Toast.LENGTH_SHORT ).show ();
                }


            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }



    public class EliminarAct extends AsyncTask<String, String, Boolean> {



        @Override
        protected Boolean doInBackground(String... params) {


            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/ActividadWebMethods?WSDL";
            String METHOD_NAME = "EliminarActi";

            String SOAP_ACTION = "http://WebService/EliminarActi";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("IDActividad", params[0]); // para agregar parámetros a nuestro método de nuestro webservice

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope( SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            envelope.dotNet = false;

            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.debug = true; // para las pruebas


            try{
                ht.call(SOAP_ACTION, envelope);
                String a = ht.requestDump;
                String b = ht.responseDump;
                Log.println(Log.INFO, "request", a);
                Log.println(Log.INFO, "response", b);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                resultado = response.toString(); // con esta variable guardamos lo que nos returne el web service
                Log.i("Respuesta" ,  resultado);
                return true; // este debería ir dentro del try
            }
            catch(Exception error){
                error.printStackTrace();
                return false;
            }


        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if(success){ // para saber que se ejecutó correctamente mi web service (success)
                if(resultado.equals("Error")){
                    Toast.makeText (Vista.getContext (),"Error ", Toast.LENGTH_SHORT ).show ();
                }else{
                    Toast.makeText (Vista.getContext (),"Checheo Exitoso ", Toast.LENGTH_SHORT ).show ();
                }


            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
