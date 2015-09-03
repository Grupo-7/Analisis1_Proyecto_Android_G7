package com.analisis.grupo7.g7;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class EventSelect extends ActionBarActivity {

    ListView lista_eventos;
    String evento_seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);

        evento_seleccionado = "";

        Button initB = (Button)findViewById(R.id.start_scan);
        initB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarEvento(evento_seleccionado)){
                    Intent intent = new Intent(getApplicationContext(),Escanner.class);
                    intent.putExtra("eventData",evento_seleccionado);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Primero debe seleccionar un evento.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        lista_eventos = (ListView)findViewById(R.id.listaEventos);

        String[] values = new String[]{"Evento1",
                "Evento2",
                "Evento3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_event_layout,
                android.R.id.text1,
                values);

        lista_eventos.setAdapter(adapter);

        this.lista_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)lista_eventos.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
                evento_seleccionado = item;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_select, menu);
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

    public boolean validarEvento(String evento){
        return !evento.equals("");
    }

}
