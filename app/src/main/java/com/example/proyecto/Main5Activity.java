package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    Context var_this = this;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


    }

    public void nuevaTarea(View view) {
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
    }

    public void verTareas(View view) {
        Intent i = new Intent(this, Main3Activity.class );
        startActivity(i);
    }

    public void cerrarSesion(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.item1) {
            final AlertDialog.Builder alerta = new AlertDialog.Builder(this);

            alerta.setTitle(R.string.cambioPass);
            final View vieww = getLayoutInflater().inflate(R.layout.nuevacontra, null);
            alerta.setView(vieww);
            alerta.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contra = (EditText)vieww.findViewById(R.id.editText5);

                    SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefe.edit();

                    editor.putString("contraseña", contra.getText().toString());
                    editor.commit();
                    Toast.makeText(var_this, R.string.borrado, Toast.LENGTH_SHORT).show();
                }
            });
            alerta.create();
            alerta.show();
        }
        if (id==R.id.item2) {
            Intent i = new Intent(this, Main2Activity.class );
            startActivity(i);
        }
        if (id==R.id.item3) {
            final AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage(R.string.acercaDe);
            alerta.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.create();
            alerta.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void cancelar(View view) {
        Intent i = new Intent(this, Main5Activity.class );
        startActivity(i);
    }

    public void cambiarContrasenia(View view) {

    }
}
