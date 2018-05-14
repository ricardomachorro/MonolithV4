package com.example.alumno.monolithmovil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class Configuracion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    EditText et_nombre, et_correo, et_edad, et_contraseña, et_pais, et_direccion;
    private TextView tituloConfig,tituloParametros,PmNombre,PmEdad,PmPais,PmDir,PmCorreo,LbNombre,LbEdad,LbPais,LbDir,LbCorreo,LbContraAntigua,LbContraNueva,LbConf;
    private EditText txtNombre,txtEdad,txtPais,txtDir,txtCorreo,txtContraAntigua,txtContraNueva,txtConf;
    private Button btnSave,btnLimp;
    Sesion sesion;
    String resultado = "";
    ConstraintLayout constraintLayout;
    Button modificar, guardar;
    ProgressDialog proceso;
    String IP = MainActivity.IP;
    String PUERTO = MainActivity.PUERTO;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Configuracion() {
        // Required empty public constructor
    }

    public static Configuracion newInstance(String param1, String param2) {
        Configuracion fragment = new Configuracion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_configuracion, container, false);
        constraintLayout = vista.findViewById(R.id.constrain);
        tituloConfig=vista.findViewById ( R.id.titlConfiguracion );

        tituloParametros=vista.findViewById ( R.id.titParametrosUsuario);
        PmNombre = vista.findViewById ( R.id.txtPmNombre );
        PmEdad=vista.findViewById ( R.id.txtPmEdad );
        PmPais=vista.findViewById ( R.id.txtPmPais );
        PmDir=vista.findViewById ( R.id.txtPmDirec );
        PmCorreo=vista.findViewById ( R.id.txtPmCorreo );
        sesion=new Sesion (vista.getContext ());
        PmNombre.setText ( "Nombre: " + sesion.getNombreUsuario ());
        PmEdad.setText ( "Edad: " + sesion.getEdad ());
        PmPais.setText ( "Pais: "+ sesion.getPais () );
        PmDir.setText ( "Direccion: "+ sesion.getDireccion ());
        PmCorreo.setText ( "Correo:" + sesion.getCorreo () );
        //LbNombre=vista.findViewById ( R.id.LbNuevoNombre );
       // LbEdad=vista.findViewById ( R.id.LbNuevaEdad );
       // LbPais=vista.findViewById ( R.id.LbNuevoPais );
       // LbDir=vista.findViewById ( R.id.LbNuevaDir );
      //  LbCorreo=vista.findViewById ( R.id.LbCorreo );
       // LbContraAntigua=vista.findViewById ( R.id.LbAntiguaCon );
       // LbContraNueva=vista.findViewById ( R.id.LbNuevaCon );
       // LbConf=vista.findViewById ( R.id.LbConfCon );
        txtNombre=vista.findViewById ( R.id.txtNuevoNombre );
        txtEdad=vista.findViewById ( R.id.txtNuevaEdad );
        txtPais=vista.findViewById ( R.id.txtNuevoPais );
        txtDir=vista.findViewById ( R.id.txtNuevaDir );
        txtCorreo=vista.findViewById ( R.id.txtCorreo );
        //txtContraAntigua=vista.findViewById ( R.id.txtAntiguaCon );
        txtContraNueva=vista.findViewById ( R.id.txtNuevaCon );
        txtConf=vista.findViewById ( R.id.txtConfCon );
        btnSave=vista.findViewById ( R.id.btnSaveConf );
        btnLimp=vista.findViewById ( R.id.btnLimpConfig );
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                String idusuario = Integer.toString(sesion.getIDUsuario()).trim();
                String Nombre = txtNombre.getText().toString().trim();
                String Edad = txtEdad.getText().toString().trim();
                String Pais = txtPais.getText().toString().trim();
                String Direccion = txtDir.getText().toString().trim();
                String Correo = txtCorreo.getText().toString().trim();
                String Contrasena = txtContraNueva.getText().toString().trim();
                if (!Nombre.isEmpty() && !Edad.isEmpty() && !Pais.isEmpty() && !Direccion.isEmpty() && !Correo.isEmpty() && !Contrasena.isEmpty()  ){
                  if(Edad.matches ( "[0-9]*"  ) && Edad.length ()<3){
                      if(Direccion.length ()>13){
                          if(Correo.matches ( "^[A-Za-z0-9+_.-]+@(.+)$" )){
                              if( Contrasena.length ()<15 && Contrasena.length ()>5){
                                  if(txtContraNueva.getText ().toString ().equalsIgnoreCase ( txtConf.getText().toString () )){
                                      proceso = new ProgressDialog ( getContext () );
                                      proceso.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
                                      proceso.setMessage ( "Guardando cambios" );
                                      proceso.setCancelable ( false );
                                      proceso.show ();
                                      new ModificarDatos ().execute ( idusuario, Nombre, Edad, Pais, Direccion, Correo, Contrasena );
                                  }else {
                                      Toast.makeText(vista.getContext (), "Las contraseñas debende ser iguales ", Toast.LENGTH_SHORT).show();
                                  }
                              }else{
                                  Toast.makeText(vista.getContext (), "Ponga una contraseña mayor a 5 caracteres y menor a 15 caracteres ", Toast.LENGTH_SHORT).show();
                              }

                          }else{
                              Toast.makeText(vista.getContext (), "Ponga un correo valido", Toast.LENGTH_SHORT).show();
                          }
                      }else{
                          Toast.makeText(vista.getContext (), "Pongauna Direccion valida", Toast.LENGTH_SHORT).show();
                      }
                  }else{
                      Toast.makeText(vista.getContext (), "Ponga una edad valida", Toast.LENGTH_SHORT).show();
                  }

                }else{
                    Toast.makeText(vista.getContext (), "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }

        });

       /* et_nombre = vista.findViewById(R.id.name);
        et_edad = vista.findViewById(R.id.edad);
        et_pais = vista.findViewById(R.id.pais);
        et_direccion = vista.findViewById(R.id.direccion);
        et_correo = vista.findViewById(R.id.correo);
        et_contraseña = vista.findViewById(R.id.contrasenia);
        constraintLayout = vista.findViewById(R.id.constrain);
       // modificar = vista.findViewById(R.id.boton1);
        guardar = vista.findViewById(R.id.boton2);
        modificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                et_nombre.setEnabled(true);
                et_edad.setEnabled(true);
                et_pais.setEnabled(true);
                et_direccion.setEnabled(true);
                et_correo.setEnabled(true);
                et_contraseña.setEnabled(true);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String idusuario = Integer.toString(sesion.getIDUsuario()).trim();
                String Nombre = et_nombre.getText().toString().trim();
                String Edad = et_edad.getText().toString().trim();
                String Pais = et_pais.getText().toString().trim();
                String Direccion = et_direccion.toString().trim();
                String Correo = et_correo.getText().toString().trim();
                String Contrasena = et_contraseña.getText().toString().trim();
                if (!Nombre.isEmpty() && !Edad.isEmpty() && !Pais.isEmpty() && !Direccion.isEmpty() && !Correo.isEmpty() && !Contrasena.isEmpty()){
                    proceso = new ProgressDialog(getContext());
                    proceso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    proceso.setMessage("Guardando cambios");
                    proceso.setCancelable(false);
                    proceso.show();
                    new ModificarDatos().execute(idusuario,Nombre, Edad, Pais, Direccion, Correo, Contrasena);
                }else{

                }
            }

        });
        et_nombre.setText(sesion.getNombreUsuario());
        et_edad.setText(sesion.getEdad());
        et_pais.setText(sesion.getPais());
        et_direccion.setText(sesion.getDireccion());
        et_correo.setText(sesion.getCorreo());
        et_contraseña.setText(sesion.getContrasena());*/
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class ModificarDatos extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {

            String valor = "";
            JSONObject datos = new JSONObject();
            try {
                datos.put("IdUsuario", params[0]);
                datos.put("Nombre", params[1]);
                datos.put("Edad", params[2]);
                datos.put("Pais",params[3]);
                datos.put("Direccion", params[4]);
                datos.put("Correo", params[5]);
                datos.put("Contrasena", params[6]);
                valor = datos.toString();
            }
            catch (Exception e){
                return false;
            }


            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/UsuarioWebMethods?WSDL";
            String METHOD_NAME = "GuardarDatosAndroid";
            String SOAP_ACTION = "http://WebService/GuardarDatosAndroid";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Datos", valor); // para agregar parámetros a nuestro método de nuestro webservice

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
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


            String NombreUsuario ;
            String Correo ;
            int Edad ;
            String Pais ;
            String Direccion ;
            String Contrasena ;

            if(success){ // para saber que se ejecutó correctamente mi web service (success)
                if(resultado.equals("error")){
                    proceso.dismiss();
                    Snackbar.make(constraintLayout, "Lo sentimos, hubo un error al guardar los datos", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else{
                    try{
                        JSONObject info = new JSONObject(resultado);
                        NombreUsuario = info.getString("NombreUsuario");
                        Correo = info.getString("Correo");
                        Edad = Integer.parseInt(info.getString("Edad"));
                        Pais = info.getString("Pais");
                        Direccion = info.getString("Direccion");
                        Contrasena = info.getString("Contrasena");
                        sesion.setNombreUsuario(NombreUsuario);
                        sesion.setCorreo(Correo);
                        sesion.setEdad(Edad);
                        sesion.setPais(Pais);
                        sesion.setDireccion(Direccion);
                        sesion.setContrasena(Contrasena);
                        PmNombre.setText ( "Nombre: " + sesion.getNombreUsuario ());
                        PmEdad.setText ( "Edad: " + sesion.getEdad ());
                        PmPais.setText ( "Pais: "+ sesion.getPais () );
                        PmDir.setText ( "Direccion: "+ sesion.getDireccion ());
                        PmCorreo.setText ( "Correo:" + sesion.getCorreo () );
                        Intent intent = new Intent(getContext().getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                    }
                    catch (JSONException error){
                        proceso.dismiss();
                        Snackbar.make(constraintLayout, "Error", Snackbar.LENGTH_LONG).show();
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