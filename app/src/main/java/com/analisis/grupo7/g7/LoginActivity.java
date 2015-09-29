package com.analisis.grupo7.g7;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class LoginActivity extends ActionBarActivity {

    private TextView usernameT;
    private TextView passwT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameT = (TextView)findViewById(R.id.username);
        passwT = (TextView)findViewById(R.id.password);

        Button loginB = (Button) findViewById(R.id.login_button);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usernameT.getText().toString();
                String pw = passwT.getText().toString();
                if(validarVacios(usuario,pw)){
                    Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                    new Loggearse(usuario,pw).execute();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe ingresar todos los datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public boolean validarVacios(String user, String pass){
        return !(user.equals("")||pass.equals(""));
    }

    private class Loggearse extends AsyncTask<String,Void,String>{

        private String carnet;
        private String pass;

        public Loggearse(String carnet, String pass){
            this.carnet = carnet;
            this.pass = pass;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            BufferedReader reader = null;

            try{
                url = new URL("http://carlosrodf.koding.io/android?op=pendiente");
                URLConnection conn = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String tmp = reader.readLine();
                tmp = tmp.replace("&quot;","\'");
                JSONObject obj = new JSONObject(tmp);
                Log.d("MSG","Servidor encontrado!");

            }catch (Exception e){
                e.printStackTrace();
                Log.d("MSG","No se encontro el sevidor");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //FALTA COMUNICACION CON LO QUE RODRIGO NO HA HECHO -.-
            if(s.equals("valido")){
                Intent intent = new Intent(getApplicationContext(),EventSelect.class);
                intent.putExtra("carnet",this.carnet);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"No valido",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
