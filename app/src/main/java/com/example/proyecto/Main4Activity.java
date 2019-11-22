package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        cargarLayouts();

        et1 = (EditText)findViewById(R.id.editText2);
        et2 = (EditText)findViewById(R.id.editText3);
    }

    public void cargarLayouts() {
        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(this, "administro", null, 1);
        SQLiteDatabase bd = administro.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        String dato = bundle.getString("tareaSeleccionada");

        et2.setText(dato);

        //Cursor fila = bd.rawQuery("select * from tareas where codigo =" + dato, null);
        /*
        if (fila.moveToFirst()) {
            do {
                et1.setText(fila.getString(1));
            } while (fila.moveToNext());
        }

         */

        bd.close();
    }
}
