package com.example.alumno.monolithmovil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Solicitudes.OnFragmentInteractionListener,Ofertas.OnFragmentInteractionListener, Actividades.OnFragmentInteractionListener,Actividades_Terminadas.OnFragmentInteractionListener,Actividades_NoTerminadas.OnFragmentInteractionListener,ActividadesCategoria.OnFragmentInteractionListener,Notas.OnFragmentInteractionListener, Logros.OnFragmentInteractionListener, Grupos.OnFragmentInteractionListener, PreConfiguracion.OnFragmentInteractionListener, Configuracion.OnFragmentInteractionListener,ContenedorFragment.OnFragmentInteractionListener,Intercambio.OnFragmentInteractionListener {
/*
*Implements es Onfreagment.Nombredelfragment
*
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    public void CerrarSesion(){
       Sesion sesion = new Sesion(this);
       sesion.clearDatos ();
        Intent SalidaPrograma = new Intent(this, MainActivity.class);
        startActivity(SalidaPrograma);
        finish();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            CerrarSesion ();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    android.support.v4.app.Fragment fragment = null;
    Boolean fragmentSelec = false;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Actividades) {
            fragment = new Actividades();
            fragmentSelec = true;
        } else if (id == R.id.Notas) {
            fragment = new Notas();
            fragmentSelec = true;
        } else if (id == R.id.Logros) {
            fragment = new ContenedorFragment();
            fragmentSelec = true;
        } else if (id == R.id.Grupos) {
            fragment = new Grupos();
            fragmentSelec = true;
        } else if (id == R.id.Configuracion) {
            fragment = new Configuracion();
            fragmentSelec = true;
        }
        else if (id == R.id.Intercambio) {
            fragment = new Intercambio();
            fragmentSelec = true;
        }
        if(fragmentSelec=true){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment ).commit();
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
