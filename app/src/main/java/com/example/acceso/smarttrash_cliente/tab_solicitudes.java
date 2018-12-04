package com.example.acceso.smarttrash_cliente;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acceso.smarttrash_cliente.Objetos.FirebaseReferencia;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class tab_solicitudes extends Fragment {


    public tab_solicitudes() {

    }

    DatabaseReference DBref;

    private EditText txtCal, txtFechaHoy;
    private Button btnCalen, btnSoli;

    //private List<> artists;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBref = FirebaseDatabase.getInstance().getReference("cliente");

        btnSoli.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addFecha();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_solicitudes, container, false);

        txtCal = (EditText) rootView.findViewById(R.id.txtProxVisit);
        txtFechaHoy = (EditText) rootView.findViewById(R.id.txtFechaActual);
        FechaHoy();
        return rootView;


    }

    private void FechaHoy() {
        Calendar cal = Calendar.getInstance();
        String formatoFecha = "d/MM/yyyy";
        DateFormat df = new SimpleDateFormat(formatoFecha);
        String date = df.format(cal.getTime());

        txtFechaHoy.setText(date);
    }


    private void addFecha() {

        String fecha = txtCal.getText().toString().trim();

        if (!TextUtils.isEmpty(fecha)) {

            String id = DBref.push().getKey();




            DBref.child(id).setValue(fecha);


            txtCal.setText("");

            //displaying a success toast
            //Toast.makeText(this, "Fecha agregada", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            //Toast.makeText(this, "Por favor ingresa la fecha", Toast.LENGTH_LONG).show();
        }

    }
}


