package com.example.alumno.monolithmovil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreConfiguracion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreConfiguracion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreConfiguracion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView tv1, tv2;
    EditText et_password;
    Button btn;
    ConstraintLayout constraintLayout;
    Typeface calibri = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/calibri.ttf");
    int contador = 0;
    Sesion sesion;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PreConfiguracion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreConfiguracion.
     */
    // TODO: Rename and change types and number of parameters
    public static PreConfiguracion newInstance(String param1, String param2) {
        PreConfiguracion fragment = new PreConfiguracion();
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
        View vista = inflater.inflate(R.layout.fragment_pre_configuracion, container, false);
        tv1 = vista.findViewById(R.id.tv1);
        tv2 = vista.findViewById(R.id.tv2);
        et_password = vista.findViewById(R.id.editText);
        constraintLayout = vista.findViewById(R.id.constrain);
        btn = vista.findViewById(R.id.boton);
        btn.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View view){
                String validacion = et_password.getText().toString();
                String correcto = sesion.getContrasena();
                if (validacion.equals(correcto)){
                    /*
                    Intent intent = new Intent(getContext(),Configuracion.class);
                    startActivity(intent);*/
                }else{
                    if (contador < 3){
                        Snackbar.make(constraintLayout, "La contraseña es incorrecta", Snackbar.LENGTH_LONG).show();
                        contador = contador + 1;
                    }
                    else{
                        Snackbar.make(constraintLayout, "Demasiados intentos", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(),Actividades.class);
                        startActivity(intent);
                    }
                }
            }

        });
        tv1.setTypeface(calibri);
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
}

