package com.example.alumno.monolithmovil.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumno.monolithmovil.MainActivity;
import com.example.alumno.monolithmovil.R;
import com.example.alumno.monolithmovil.Sesion;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class AdapCatAct extends BaseAdapter {

    private static LayoutInflater infleter=null;

    Context contexto;
    ArrayList<CategoriasCartasAct> lista= new ArrayList<CategoriasCartasAct> (  );
    String IP = MainActivity.IP;
    String PUERTO = MainActivity.PUERTO;
    String resultado;
    Sesion sesion;
    View Vista;

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

         Vista=infleter.inflate ( R.layout.carta_categoria,null);
        TextView titulo=Vista.findViewById ( R.id.ActFinTitulo );
        ImageView img= Vista.findViewById (R.id.imagenCategoriaAct);
        titulo.setText ( lista.get ( position ).getNombre () );
        ImageButton btnEliminar=Vista.findViewById ( R.id.BtnCatActEliminar );
        btnEliminar.setTag ( lista.get ( position ).Identificador );
        btnEliminar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                     new EliminarAct ().execute ( v.getTag ().toString () );
            }
        } );
        return Vista;
    }

    public class EliminarAct extends AsyncTask<String, String, Boolean> {



        @Override
        protected Boolean doInBackground(String... params) {


            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/ActividadWebMethods?WSDL";
            String METHOD_NAME = "EliminarCategoria";

            String SOAP_ACTION = "http://WebService/EliminarCategoria";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("IDCategoria", params[0]); // para agregar parámetros a nuestro método de nuestro webservice

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
                    Toast.makeText (Vista.getContext (),"Categoria  ELiminada exitosamente" +
                            "", Toast.LENGTH_SHORT ).show ();
                }


            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void NotifyChange() {

        notifyDataSetChanged();
    }
}
