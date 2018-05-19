package com.example.alumno.monolithmovil;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alumno.monolithmovil.adapters.AdapCatAct;
import com.example.alumno.monolithmovil.adapters.CategoriasCartasAct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActividadesCategoria.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActividadesCategoria#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActividadesCategoria extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView ListViewCartas;
    ArrayList<CategoriasCartasAct> listaCartas=new ArrayList<CategoriasCartasAct> (  );
    Sesion sesion;
    String IP = MainActivity.IP;
    String PUERTO = MainActivity.PUERTO;
    String resultado = "";
    ProgressDialog proceso;
    TextView txtTitulo;
    ConstraintLayout constrain;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActividadesCategoria() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActividadesCategoria.
     */
    // TODO: Rename and change types and number of parameters
    public static ActividadesCategoria newInstance(String param1, String param2) {
        ActividadesCategoria fragment = new ActividadesCategoria ();
        Bundle args = new Bundle ();
        args.putString ( ARG_PARAM1, param1 );
        args.putString ( ARG_PARAM2, param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
            mParam1 = getArguments ().getString ( ARG_PARAM1 );
            mParam2 = getArguments ().getString ( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_actividades_categoria, container, false);
        sesion=new Sesion (view.getContext ());
        ListViewCartas=view.findViewById ( R.id.ListaViewCatAct );
        constrain=view.findViewById ( R.id.constraintActCat);
        txtTitulo=view.findViewById ( R.id.TitleCatActividades );
        new LLenarListView (ListViewCartas.getContext (),ListViewCartas).execute ( Integer.toString ( sesion.getIDUsuario () ) );
        proceso = new ProgressDialog ( getContext () );
        proceso.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
        proceso.setMessage ( "Cargando  Categorias" );
        proceso.setCancelable ( false );
        proceso.show ();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction ( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    public void LlenarCartas(ArrayList<CategoriasCartasAct> array){
        listaCartas=array;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class LLenarListView extends AsyncTask<String, String, Boolean> {

        public View Vista;
        public Context contexto;

        public LLenarListView(Context contexto,View rootView){
            this.Vista=rootView;
            this.contexto=contexto;
        }

        @Override
        protected Boolean doInBackground(String... params) {




            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/ActividadWebMethods?WSDL";
            String METHOD_NAME = "ConsultarCat";
            String SOAP_ACTION = "http://WebService/ConsultarCat";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("UsuarioID", params[0]); // para agregar parámetros a nuestro método de nuestro webservice

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
                    proceso.dismiss();
                    Snackbar.make(constrain, "Lo sentimos, hubo un error al cargar los Datos", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else{
                    try{
                        ArrayList<CategoriasCartasAct> array=new ArrayList<> (  );
                        JSONObject info = new JSONObject(resultado);
                        JSONArray jsonLista=info.getJSONArray ("Actividades"  );
                        for(int i=0;i<jsonLista.length ()-1;i++){
                            JSONObject json_obj = jsonLista.getJSONObject(i);

                                String Categoria=json_obj.getString ( "Categoria" );
                                CategoriasCartasAct cat=new CategoriasCartasAct ( Categoria );
                                array.add ( cat );



                        }
                        LlenarCartas ( array );
                        AdapCatAct adapte=new AdapCatAct ( Vista.getContext (), listaCartas);
                        ListViewCartas.setAdapter ( adapte) ;
                        adapte.notifyDataSetChanged ();
                        proceso.dismiss ();

                    }
                    catch (JSONException error){
                        proceso.dismiss();
                        Snackbar.make(constrain, "Error", Snackbar.LENGTH_LONG).show();
                        return;
                    }

                }

            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
