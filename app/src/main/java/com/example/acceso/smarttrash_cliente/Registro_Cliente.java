package com.example.acceso.smarttrash_cliente;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Registro_Cliente extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView =inflater.inflate(R.layout.tab_solicitudes,container,false);
        super.onCreateView(inflater, container, savedInstanceState);

        return null;
    }
}
