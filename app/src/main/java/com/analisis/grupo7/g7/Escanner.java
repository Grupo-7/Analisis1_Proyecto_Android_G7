package com.analisis.grupo7.g7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

    public void ingresoManual(View view){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.setTitle("Ingreso manual");

        Button cancelB = (Button)dialog.findViewById(R.id.dialog_cancelB);
        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button sendB = (Button)dialog.findViewById(R.id.dialog_sendB);
        sendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView carnetT = (TextView)dialog.findViewById(R.id.dialog_carnet);
                TextView passT = (TextView)dialog.findViewById(R.id.dialog_pass);

                if(fullValidar(carnetT.getText().toString())){
                    if(passT.getText().toString().equals("admin")){
                        Toast.makeText(getApplicationContext(),"Enviando datos...",Toast.LENGTH_SHORT).show();
                        //AsyncTask
                        new EnviarAsistencia(carnetT.getText().toString()).execute();
                        dialog.dismiss();

                    }else{
                        Toast.makeText(getApplicationContext(),"Contraseña no valida!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Carnet no valido!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialog.show();
    }

    public boolean fullValidar(String carnet){
        return validarCarnet(carnet) && validarLargoCarnet(carnet);
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

    private class EnviarAsistencia extends AsyncTask<String,Void,String>{

        private String carnet;

        public EnviarAsistencia(String carnet) {
            this.carnet = carnet;
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            BufferedReader reader = null;
            try{
                url = new URL("http://carlosrodf.koding.io/android?op=asistir&evento=7&carnet="+this.carnet);
                URLConnection conn = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                reader.readLine();
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
