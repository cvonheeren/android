package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText3);

        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefe.edit();

        et1.setText(prefe.getString("usuario","pepe"));
        if (prefe.getString("contraseña", "") == "") {
            editor.putString("contraseña", "12345");
        }

        editor.commit();
    }

    public void logear(View view) {
        SharedPreferences preferencias = getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        if (preferencias.getString("contraseña", "").equals(et2.getText().toString())) {
            editor.putString("usuario", et1.getText().toString());
            editor.commit();

            Intent i = new Intent(this, Main5Activity.class );
            startActivity(i);
        } else {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
}
