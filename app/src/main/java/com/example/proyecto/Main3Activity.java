package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private ListView lv1;
    private TextView tv1;
    Context var_this = this;

    public static ArrayList<Tarea> tareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv1 = (ListView)findViewById(R.id.lv1);
        tv1 = findViewById(R.id.textView7);

        tareas = cargarTareas();
        if (tareas != null) {
            AdaptadorTareas adaptador = new AdaptadorTareas(this);
            lv1.setAdapter(adaptador);
        } else {
            Toast.makeText(this, R.string.aviso1, Toast.LENGTH_SHORT).show();
        }


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(var_this, Main4Activity.class);
                in.putExtra("tareaSeleccionada",String.valueOf(tareas.get(i).getCod()));
                Toast.makeText(var_this, String.valueOf(tareas.get(i).getCod()), Toast.LENGTH_SHORT).show();

                startActivity(in);
            }
        });

        registerForContextMenu(lv1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.i1:
                final Intent i = new Intent(this, Main2Activity.class );
                startActivity(i);
                break;
            case R.id.i2:
                final AlertDialog.Builder alerta = new AlertDialog.Builder(this);

                alerta.setTitle(R.string.advertencia);
                alerta.setMessage(R.string.preguntaBorrar);
                alerta.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(var_this, "administro", null, 1);
                        SQLiteDatabase bd = administro.getWritableDatabase();
                        //pillar el indice seleccionado?
                        //bd.execSQL("delete from tareas where codigo="+lv1.getSelectedItem().);
                        bd.close();

                        Intent i = new Intent(var_this, Main3Activity.class );
                        startActivity(i);

                        Toast.makeText(var_this, R.string.borrado, Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(var_this, R.string.noBorrado, Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.create();
                alerta.show();
                break;
        }
        return true;
    }

    /*
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();


    }

     */

    public void done(View view) {
        Intent i = new Intent(this, Main3Activity.class );
        startActivity(i);
    }

    public void noDone(View view) {


        Intent i = new Intent(this, Main3Activity.class );
        startActivity(i);
    }

    public  ArrayList<Tarea> cargarTareas() {
        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(this, "administro", null, 1);
        SQLiteDatabase bd = administro.getWritableDatabase();

        ArrayList<Tarea> acumulador = new ArrayList<>();

        Cursor fila = bd.rawQuery("select * from tareas", null);

        if (fila.moveToFirst()) {
            do {
                Tarea tarea = new Tarea(fila.getInt(0), fila.getString(1), fila.getString(2) , fila.getString(3), fila.getString(4), fila.getFloat(5), fila.getInt(6));
                acumulador.add(tarea);
            } while (fila.moveToNext());
        }

        return acumulador;
    }

    public  ArrayList<Tarea> cargarTareasHechas() {
        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(this, "administro", null, 1);
        SQLiteDatabase bd = administro.getWritableDatabase();

        ArrayList<Tarea> acumulador = new ArrayList<>();

        Cursor fila = bd.rawQuery("select * from tareas where hecha=1", null);

        if (fila.moveToFirst()) {
            acumulador.clear();
            do {
                Tarea tarea = new Tarea(fila.getInt(0), fila.getString(1), fila.getString(2) , fila.getString(3), fila.getString(4), fila.getFloat(5), fila.getInt(6));
                acumulador.add(tarea);
            } while (fila.moveToNext());
        }

        return acumulador;
    }

    public  ArrayList<Tarea> cargarTareasNoHechas() {
        AdminSQLiteOpenHelper administro = new AdminSQLiteOpenHelper(this, "administro", null, 1);
        SQLiteDatabase bd = administro.getWritableDatabase();

        ArrayList<Tarea> acumulador = new ArrayList<>();

        Cursor fila = bd.rawQuery("select * from tareas where hecha=0", null);

        if (fila.moveToFirst()) {
            acumulador.clear();
            do {
                Tarea tarea = new Tarea(fila.getInt(0), fila.getString(1), fila.getString(2) , fila.getString(3), fila.getString(4), fila.getFloat(5), fila.getInt(6));
                acumulador.add(tarea);
            } while (fila.moveToNext());
        }

        return acumulador;
    }

    class AdaptadorTareas extends ArrayAdapter<Tarea> {
        AppCompatActivity appCompatActivity;

        AdaptadorTareas(AppCompatActivity context) {
            super(context, R.layout.tarea, tareas);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.tarea, null);
            TextView textView1 = (TextView)item.findViewById(R.id.textView);
            TextView textView2 = (TextView)item.findViewById(R.id.textView12);
            textView1.setText(tareas.get(position).getNombre());
            textView2.setText(String.valueOf(tareas.get(position).getCod()));
            ImageView imageView1 = (ImageView)item.findViewById(R.id.imageView);
            imageView1.setImageResource(R.mipmap.liston);

            return(item);
        }
    }
}