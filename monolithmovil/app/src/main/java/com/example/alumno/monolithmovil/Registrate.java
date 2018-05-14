package com.example.alumno.monolithmovil;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumno.monolithmovil.clases.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

public class Registrate extends AppCompatActivity {

    EditText et_nombre, et_correo, et_edad, et_contraseña, et_pais, et_direccion;
    private Sesion sesion;
    String resultado = "";
    ProgressDialog proceso;
    public  String IP = MainActivity.IP;
    public  String PUERTO = MainActivity.PUERTO;
    ConstraintLayout constraintLayout;
   ProcesoRegistro procesoRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);
        et_nombre = findViewById(R.id.name);
        et_correo = findViewById(R.id.correo);
        et_edad = findViewById(R.id.edad);
        et_contraseña = findViewById(R.id.contraseña);
        et_pais = findViewById(R.id.pais);
        et_direccion = findViewById(R.id.direccion);
        constraintLayout = findViewById(R.id.vista);
        //String [] opciones = {"8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65"};
        //ArrayAdapter <String> miArray= new ArrayAdapter<>(this,R.layout.spinner_item_alex, opciones);
        //sp_edad.setAdapter(miArray);
    }

    // Metodo para el botón registrar

    public void Registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Proyecto", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // La abrimos para leerla (Modo lectura y escritura)

        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        String pais = et_pais.getText().toString();
        String direccion = et_direccion.getText().toString();

        if (!nombre.isEmpty() && !correo.isEmpty() && !pais.isEmpty() && ! direccion.isEmpty() && !contraseña.isEmpty() && !edad.isEmpty()){
                 if(edad.matches ( "[0-9]*" ) && edad.length ()<3){
                     if(!pais.matches ( "[0-9]*" )){
                         if(direccion.length ()>13){
                              if(correo.matches (  "^[A-Za-z0-9+_.-]+@(.+)$" )){
                                  if(contraseña.length ()>5 && contraseña.length ()<15){
                                      sesion = new Sesion(this);
                                       Registro ( nombre,edad,pais,direccion,correo,contraseña );
                                     // Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                     // Intent intent = new Intent(this, MenuPrincipal.class);
                                     // startActivity(intent);
                                  }else{
                                      Toast.makeText(this, "Debes poner una contraseña mayor a 5 caracteres y menor a 15 caracteres", Toast.LENGTH_SHORT).show();

                                  }
                              }else{
                                  Toast.makeText(this, "Debes poner un correo valido", Toast.LENGTH_SHORT).show();

                              }
                         }else{
                             Toast.makeText(this, "Debes poner una direccion valida", Toast.LENGTH_SHORT).show();

                         }
                     }else{
                         Toast.makeText(this, "Debes poner un pais valido", Toast.LENGTH_SHORT).show();

                     }
                 }else{
                     Toast.makeText(this, "Debes poner una edad valida", Toast.LENGTH_SHORT).show();
                     BaseDeDatos.close();
                 }
                    /*
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombre);
                    registro.put("edad",edad);
                    registro.put("pais", pais);
                    registro.put("direccion", direccion);
                    registro.put("correo", correo);
                    registro.put("contrasenia", contraseña);
                    BaseDeDatos.insert("personas", null, registro);
                    BaseDeDatos.close();
                    Toast.makeText(this, "Registro hecho exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuPrincipal.class);
                    startActivity(intent);*/
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }

    }

    // Metodo para el boton Regresar

    public  void Regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void Registro(String Nombre,String  Edad ,String Pais,String Direc,String Correo,String Contra) {
        MyTaskParams task=new MyTaskParams ( Nombre.toString ().trim (),Edad.toString ().trim (),Pais.toString ().trim (),
                Direc.toString ().trim (),Correo.toString ().trim (),Contra.toString ().trim () );
        procesoRegistro=new ProcesoRegistro();
        procesoRegistro.execute(task);
        proceso = new ProgressDialog(Registrate.this);
        proceso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        proceso.setMessage("Procesando Registro");
        proceso.setCancelable(false);
        proceso.show();
    }


    private static class MyTaskParams {
        String NombreUs,EdadUsu,PaisUsu,DirecUsu,CorreoUsu,ContraUsu;

        public MyTaskParams(String nombreUs, String edadUsu, String paisUsu, String direcUsu, String correoUsu, String contraUsu) {
            NombreUs = nombreUs;
            EdadUsu = edadUsu;
            PaisUsu = paisUsu;
            DirecUsu = direcUsu;
            CorreoUsu = correoUsu;
            ContraUsu = contraUsu;
        }

        public String getNombreUs() {
            return NombreUs;
        }

        public void setNombreUs(String nombreUs) {
            NombreUs = nombreUs;
        }

        public String getEdadUsu() {
            return EdadUsu;
        }

        public void setEdadUsu(String edadUsu) {
            EdadUsu = edadUsu;
        }

        public String getPaisUsu() {
            return PaisUsu;
        }

        public void setPaisUsu(String paisUsu) {
            PaisUsu = paisUsu;
        }

        public String getDirecUsu() {
            return DirecUsu;
        }

        public void setDirecUsu(String direcUsu) {
            DirecUsu = direcUsu;
        }

        public String getCorreoUsu() {
            return CorreoUsu;
        }

        public void setCorreoUsu(String correoUsu) {
            CorreoUsu = correoUsu;
        }

        public String getContraUsu() {
            return ContraUsu;
        }

        public void setContraUsu(String contraUsu) {
            ContraUsu = contraUsu;
        }
    }


    public class ProcesoRegistro extends AsyncTask<MyTaskParams,String, Boolean> {
        @Override
        public  Boolean doInBackground(MyTaskParams... myTaskParams) {
            String valor ="";
            JSONObject datos = new JSONObject();
            try {
                datos.put("NombreUsuario", myTaskParams[0].getNombreUs ());
                datos.put("EdadUsuario", myTaskParams[0].getEdadUsu ());
                datos.put("PaisUsuario", myTaskParams[0].getPaisUsu ());
                datos.put("DirecUsuario", myTaskParams[0].getDirecUsu ());
                datos.put("CorreoUsuario", myTaskParams[0].getCorreoUsu ());
                datos.put("ContraUsuario",myTaskParams[0].getContraUsu ());
                valor = datos.toString();
            }
            catch (Exception e){
                return false;
            }


            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/UsuarioWebMethods?WSDL";
            String METHOD_NAME = "RegistroUsuarioMovil";
            String SOAP_ACTION = "http://UsuarioWebMethods/RegistroUsuarioMovil";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Usuario", valor); // para agregar parámetros a nuestro método de nuestro webservice

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
                SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
                resultado = response.toString(); // con esta variable guardamos lo que nos returne el web service
                Log.i("Respuesta" ,  resultado);

            }
            catch(Exception error){
                error.printStackTrace();
                return false;
            }
            return true;


           /*String valor ;
            SoapObject request=new SoapObject ( NAMESPACE,METHOD_NAME );
            request.addProperty ( "NombreUsuario",params[0] );
            request.addProperty ( "DirecUsuario",params[1] );
            request.addProperty ( "EdadUsuario",params[2] );
            request.addProperty ( "PaisUsuario",params[3] );
            request.addProperty ( "CorreoUsuario",params[4] );
            request.addProperty ( "ContraUsuario",params[5] );
            SoapSerializationEnvelope envelope=new SoapSerializationEnvelope ( SoapEnvelope.VER11 );

            envelope.dotNet=false;
            envelope.setOutputSoapObject ( request );
            HttpTransportSE transporte=new HttpTransportSE ( URL,160000 );
            Log.d("transporte",request.toString ());
            try{
                transporte.call ( SOAP_ACTION,envelope );
                SoapObject response=(SoapObject)envelope.getResponse ();
                Log.d ( "reps",response.toString () );
                Vector<?> responseVector=(Vector<?>) response.getProperty ( 0 );
                int count=responseVector.size ();
                Log.d("vectorResponse",String.valueOf ( count ));

            }catch(Exception e){

                Log.d("eXXX",e.getMessage ());
            }

            /

            JSONObject datos = new JSONObject();
            try {
                datos.put("NombreUsuario", params[0]);
                datos.put("DirecUsuario",params[1]);
                datos.put("EdadUsuario",params[2]);
                datos.put("PaisUsuario",params[3]);
                datos.put("CorreoUsuario", params[4]);
                datos.put("ContraUsuario", params[5]);

                //valor = datos.toString();
            }
            catch (Exception e){
                return false;
            }





            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // request.addProperty("numero", valor); // para agregar parámetros a nuestro método de nuestro webservice
             request.addProperty ("NombreUsuario", params[0]  );
             request.addProperty ( "DirecUsuario",params[1] );
             request.addProperty ( "EdadUsuario",params[2] );
             request.addProperty ( "PaisUsuario",params[3] );
             request.addProperty ( "CorreoUsuario", params[4] );
             request.addProperty ( "ContraUsuario", params[5] );
             
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
            }
            catch(Exception error){
                error.printStackTrace();
                return false;
            }

            return true;*/
        }



        @Override
        protected void onPostExecute(final Boolean success) {
            int IDUsuario ;
            String NombreUsuario ;
            String Correo ;
            int Edad ;
            String Pais ;
            String Direccion ;
            String Contrasena ;
            int TipoUsuario ;
            String Validado ;
            int Puntos ;

            if(success){ // para saber que se ejecutó correctamente mi web service (success)
                if(resultado.equals("incorrecto") || resultado.equals("Error") || resultado.equals("Repetido")){
                    if(resultado.equals("incorrecto") || resultado.equals("Error")){
                        proceso.dismiss();
                        Snackbar.make(constraintLayout, "Error de Registro", Snackbar.LENGTH_LONG).show();
                        return;
                    }else if(resultado.equals("Repetido")){
                        proceso.dismiss();
                        Snackbar.make(constraintLayout, "Usuario repetido", Snackbar.LENGTH_LONG).show();

                        return;
                    }

                }else{
                    try{
                        JSONObject info = new JSONObject(resultado);
                        IDUsuario = Integer.parseInt(info.getString("IDUsuario"));
                        NombreUsuario = info.getString("NombreUsuario");
                        Utilidades.usuario=NombreUsuario;
                        Correo = info.getString("Correo");
                        Edad = Integer.parseInt(info.getString("Edad"));
                        Pais = info.getString("Pais");
                        Direccion = info.getString("Direccion");
                        Contrasena = info.getString("Contrasena");
                        TipoUsuario = Integer.parseInt(info.getString("TipoUsuario"));
                        Validado = info.getString("Validado");
                        Puntos = Integer.parseInt(info.getString("Puntos"));
                        try{
                            sesion.setDatos(IDUsuario, NombreUsuario, Correo, Edad, Pais, Direccion, Contrasena, TipoUsuario, Validado, Puntos);
                            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                            startActivity(intent);
                            finish();
                        }catch(Exception ex){
                            ex.printStackTrace();
                            return;
                        }

                    }
                    catch (JSONException error){
                        Snackbar.make(constraintLayout, "Error", Snackbar.LENGTH_LONG).show();
                        proceso.dismiss();
                        return;
                    }
                }





            }
            else{
                proceso.dismiss();
                Snackbar.make(constraintLayout, "Error de Registro", Snackbar.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

        }

    }

}