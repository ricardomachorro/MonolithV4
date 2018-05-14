package com.example.alumno.monolithmovil.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INSPIRON on 02/05/2018.
 */

public class SeccionesAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> listafragment=new ArrayList<>();
    private final List<String> listatitulos=new ArrayList<>();
    public SeccionesAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String titulo){
        listafragment.add(fragment);
        listatitulos.add(titulo);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listatitulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listafragment.get(position);
    }

    @Override
    public int getCount() {
        return listafragment.size();
    }
}
