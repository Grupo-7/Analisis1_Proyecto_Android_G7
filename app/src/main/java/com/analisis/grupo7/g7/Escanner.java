package com.analisis.grupo7.g7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Escanner extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanner);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("eventData")!=null)
            Toast.makeText(getApplicationContext(), "Inicio correctamente [" + bundle.getString("eventData") + "]", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Ocurrio un error.", Toast.LENGTH_LONG).show();
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Esta seguro que desea finalizar la toma de asistencia?");

        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Toma de asistencia finalizada con exito.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_escanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validarCarnet(String carnet){
        try{
            Long.parseLong(carnet);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean validarLargoCarnet(String carnet){
        return carnet.length()==9;
    }
}
