package com.example.vicjar.calculadorav2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton reconoce=(ImageButton) findViewById(R.id.imageButton);
        reconoce.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//Este es el intent del reconocimiento de voz
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//Especificamos el idioma, en esta ocasión probé con el de Estados Unidos
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //Iniciamos la actividad dentro de un Try en caso surja un error.
                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onActivityResult(int requestcode, int resultcode, Intent datos)
    {
// Si el reconocimiento de voz es correcto almacenamos dentro de un array los datos obtenidos.
//Mostramos en pantalla el texto obtenido de la posición 0.
        if (resultcode== Activity.RESULT_OK && datos!=null)
        {
            ArrayList<String> text=datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            String[] palabra = new String[text.size()];

            String joined = TextUtils.join(", ", text);
            String palabraLimpia = "";
            int i=0;
            while(joined.charAt(i) != ',')
            {
                System.out.print("si entra");
                palabraLimpia+=joined.charAt(i);
                i++;
            }

            int suma = 0;
            for(i=0;i<palabraLimpia.length();i++)
            {
                if (palabraLimpia.charAt(i)==' ')
                {
                    i++;
                }
                if(palabraLimpia.charAt(i)=='m' && palabraLimpia.charAt(i+1)=='e')
                {
                    i+=6;
                    suma -= Integer.parseInt(""+palabraLimpia.charAt(i));
                }
                else
                {
                    if(palabraLimpia.charAt(i)=='m' && palabraLimpia.charAt(i+1)=='á')
                    {
                        i+=4;
                        suma += Integer.parseInt(""+palabraLimpia.charAt(i));
                    }
                    else
                    {
                        suma += suma += Integer.parseInt(""+palabraLimpia.charAt(i));
                    }
                }
            }

           String cadena = String.valueOf(suma);
            //String hola="que tranza";
           // ArrayList<String> myList = new ArrayList<String>(palabra.Arrays(palabra.split(" ")));
           // List<String> myList = new ArrayList<String>(Arrays.asList(palabra.split(" ")));
            //Toast.makeText(this,text.get(0),Toast.LENGTH_LONG).show();
            Toast.makeText(this,cadena,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}