package com.example.alumno.monolithmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumno.monolithmovil.clases.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {
    EditText et_nombre, et_contraseña;
    Button xd ;
    private static Sesion sesion;
    String resultado = "";
    ProgressDialog proceso;
    ConstraintLayout constraintLayout;
    public static final String IP = "192.168.43.100";
    public static final String PUERTO = "8080";
    obtenerDatos datos;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint);
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
        sesion = new Sesion(this);
        if(sesion.getIDUsuario() != 0){
            Intent inicioAlumno = new Intent(this, MenuPrincipal.class);
            startActivity(inicioAlumno);
            finish();
            return;
        }

        Typeface calibri = Typeface.createFromAsset(getAssets(),  "fonts/calibri.ttf");
        TextView titulo = findViewById(R.id.textView3);
        titulo.setTypeface(calibri);
        //titulo.setTypeface(calibri, Typeface.BOLD);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void iniciarSesion(View view) {
        //datos = new obtenerDatos();
        //datos.execute(et_nombre.getText().toString().trim(), et_contraseña.getText().toString().trim());
        new obtenerDatos().execute(et_nombre.getText().toString().trim(), et_contraseña.getText().toString().trim());;
        proceso = new ProgressDialog(MainActivity.this);
        proceso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        proceso.setMessage("Iniciando Sesión");
        proceso.setCancelable(false);
        proceso.show();
    }
    /*
    @Override
    protected void onPause() {
        super.onPause();
        datos.cancel(true);
    }*/

    public void abrirCorreo(View view) {
        Intent abrirCorreo = new Intent(MainActivity.this, RecuperarPassword.class);
        startActivity(abrirCorreo);
        finish();
    }

    public class obtenerDatos extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {

            String valor = "";
            JSONObject datos = new JSONObject();
            try {
                datos.put("usuario", params[0]);
                datos.put("contrasena", params[1]);
                valor = datos.toString();
            }
            catch (Exception e){
                return false;
            }


            //ws inicio sesion
            String NAMESPACE = "http://WebService/";
            String URL = "http://"+IP+":"+PUERTO+"/MonolithV4/InicioSesion?WSDL";
            String METHOD_NAME = "validarUsuarioAndroid";
            String SOAP_ACTION = "http://WebService/validarUsuarioAndroid";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("numero", valor); // para agregar parámetros a nuestro método de nuestro webservice

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
                if(resultado.equals("incorrecto") || resultado.equals("error")){
                    proceso.dismiss();
                    Snackbar.make(constraintLayout, "Usuario o contraseña incorrecta", Snackbar.LENGTH_LONG).show();
                    return;
                }


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

                    sesion.setDatos(IDUsuario, NombreUsuario, Correo, Edad, Pais, Direccion, Contrasena, TipoUsuario, Validado, Puntos);
                    Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                    startActivity(intent);
                    finish();
                }
                catch (JSONException error){
                    Snackbar.make(constraintLayout, "Error", Snackbar.LENGTH_LONG).show();
                    proceso.dismiss();
                    return;
                }

            }
            else{


                proceso.dismiss();
                Snackbar.make(constraintLayout, "Usuario o contraseña incorrecta", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
