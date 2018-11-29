package com.example.acceso.smarttrash_cliente;


import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acceso.smarttrash_cliente.Objetos.FirebaseReferencia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login_Cliente extends AppCompatActivity {






    private EditText txtPass, txtCorreo;
    private Button btnConect;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__cliente);

        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        //DatabaseReference MyRef = DB.getReference(FirebaseReferencia.BD_Referencia);

        txtPass = (EditText) findViewById(R.id.password);
        txtCorreo = (EditText) findViewById(R.id.email);

        btnConect = (Button) findViewById(R.id.btnlogin);

        firebaseAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null){
//                    Toast.makeText(Login_Cliente.this, "Sesion Iniciado", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(Login_Cliente.this, "Sesion cerrado", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

        Button mEmailSignInButton = (Button) findViewById(R.id.btnlogin);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pantalla = new Intent(Login_Cliente.this, activity_Dashboard.class);
                startActivity(pantalla);
            }
        });

    }

    private void iniciarSesion (View view){
        String Email = txtCorreo.getText().toString();
        String Pass = txtPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task task) {
                if (!task.isSuccessful()){
                    Toast.makeText(Login_Cliente.this, "Error", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login_Cliente.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}

