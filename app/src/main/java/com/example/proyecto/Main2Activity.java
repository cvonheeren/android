package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class Main2Activity extends AppCompatActivity {

    private EditText et1, et2, et3, et4;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = (Spinner)findViewById(R.id.spinner);
        et1 = (EditText)findViewById(R.id.editText6);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText9);
        et4 = (EditText)findViewById(R.id.editText12);
        setSpinner();
    }

    void setSpinner() {
        String[] opciones = {getString(R.string.Urgente), getString(R.string.Alta), getString(R.string.Media), getString(R.string.Baja)};

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);
    }

    public void regitrarTarea(View view) {
        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(this, "administro", null, 1);
        SQLiteDatabase bd = administro.getWritableDatabase();

        String nombre = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String fecha = et3.getText().toString();
        String prioridad = spinner.getSelectedItem().toString();
        float valor = Float.parseFloat(et4.getText().toString());

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("fecha", fecha);
        registro.put("prioridad", prioridad);
        registro.put("valor", valor);

        if (bd.insert("tareas", null, registro) == -1)
            Toast.makeText(this, R.string.aviso2, Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, R.string.aviso3, Toast.LENGTH_SHORT).show();

            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }
        bd.close();
    }

    public void cancelar(View view) {
        Intent i = new Intent(this, Main5Activity.class );
        startActivity(i);
    }

}
