package com.example.acceso.smarttrash_cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class menu_dashboard extends AppCompatActivity {

    private MenuItem btnlogOut;

    private FirebaseAuth auth;
//    setContentView(R.menu.menu_dashboard);
//
//    auth = FirebaseAuth.getInstance();
//
//    btnlogOut = (MenuItem) findViewById(R.id.action_logout);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);

        auth = FirebaseAuth.getInstance();

        btnlogOut = (MenuItem) findViewById(R.id.action_logout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;
            case R.id.action_logout:
                signOut();
//                Intent cerrar=new Intent(menu_dashboard.this, Login_Cliente.class);
//                stopService(cerrar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        auth.signOut();
        Intent intent = new Intent(menu_dashboard.this, Login_Cliente.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Has cerrado sesion", Toast.LENGTH_SHORT).show();
        //updateUI(null);
    }

}
