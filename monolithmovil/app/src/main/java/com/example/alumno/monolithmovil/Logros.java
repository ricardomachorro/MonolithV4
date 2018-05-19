package com.example.alumno.monolithmovil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Logros.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Logros#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Logros extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AppBarLayout appBar;

    private OnFragmentInteractionListener mListener;

    public Logros() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Logros.
     */
    // TODO: Rename and change types and number of parameters
    public static Logros newInstance(String param1, String param2) {
        Logros fragment = new Logros();
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

    public CardView logro1;
    public CardView logro2;
    public CardView logro3;
    public Button btn;
    public TextView text;
    public TextView texttitu;
    public WebView webView1;
    public WebView webView2;
    ProgressBar progreso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_logros, container, false);
        btn=(Button)view.findViewById(R.id.btnagregar);
        View parent = (View) container.getParent();
        appBar=(AppBarLayout) parent.findViewById(R.id.appBar);

        texttitu=(TextView) view.findViewById(R.id.titulotxt);
        texttitu.setText("Logros de "+ Utilidades.usuario);
        webView1=(WebView) view.findViewById(R.id.web);
        webView2=(WebView)view.findViewById(R.id.weblogros);
        webView2.loadUrl("http://"+MainActivity.IP+":8080/MonolithV4/prueba/logrover.jsp?usuario="+ Utilidades.usuario+"");
        btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                cargarWebservice();
            }
        });
        //memo(view);
        return view;
    }

    private void cargarWebservice() {


        String url="http://"+MainActivity.IP+":8080/MonolithV4/prueba/agrelogro.jsp?nombre="+ Utilidades.usuario+"";
        webView2.loadUrl("http://"+MainActivity.IP+":8080/MonolithV4/prueba/logrover.jsp?usuario="+ Utilidades.usuario+"");
        webView1.loadUrl(url);
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
