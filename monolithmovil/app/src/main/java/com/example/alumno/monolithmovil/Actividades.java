package com.example.alumno.monolithmovil;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alumno.monolithmovil.adapters.SeccionesAct;
import com.example.alumno.monolithmovil.adapters.SeccionesAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Actividades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Actividades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividades extends Fragment {
     // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;

    private ContenedorFragment.OnFragmentInteractionListener mListener;

    public Actividades() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividades newInstance(String param1, String param2) {
       Actividades fragment = new Actividades ();
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
        vista= inflater.inflate(R.layout.fragment_actividades, container, false);

        if(Utilidades.rotacion==0){
            View parent = (View) container.getParent();
            if(appBar==null){
                appBar=(AppBarLayout) parent.findViewById(R.id.appBar);
                pestañas=new TabLayout(getActivity());
                appBar.removeViews ( 1,appBar.getChildCount ()-1 );
                pestañas.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
                appBar.addView(pestañas);
                viewPager=(ViewPager) vista.findViewById(R.id.viewActivividades);
                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestañas.setupWithViewPager(viewPager);
            }
            pestañas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            Utilidades.rotacion=1;
        }

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(Utilidades.rotacion==0){
            appBar.removeView(pestañas);
        }
    }

    private void llenarViewPager(ViewPager viewPager) {
        SeccionesAdapter adapter = new SeccionesAdapter(getFragmentManager());
        adapter.addFragment(new Actividades_Terminadas (),"Actividades Terminada");
      //  adapter.addFragment(new Actividades_NoTerminadas (),"Actividades No Terminadas");
       adapter.addFragment(new ActividadesCategoria (),"Categorias");
        viewPager.setAdapter(adapter);
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
        if (context instanceof ContenedorFragment.OnFragmentInteractionListener) {
            mListener = (ContenedorFragment.OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}



