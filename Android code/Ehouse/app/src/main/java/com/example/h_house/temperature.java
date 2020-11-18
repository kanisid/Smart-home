
package com.example.h_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Toast;


public class temperature extends AppCompatActivity   {

    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);


        if((MainActivity.type.equals("temperature"))&&(MainActivity.etat.equals("1")))
        {
            findViewById(R.id.ONtemp).setBackgroundColor(Color.GREEN);
            findViewById(R.id.OFFtemp).setBackgroundColor(Color.WHITE);
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("Température : " + MainActivity.valeur + " °C");
        }
        else
        {
            findViewById(R.id.ONtemp).setBackgroundColor(Color.WHITE);
            findViewById(R.id.OFFtemp).setBackgroundColor(Color.RED);
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("Température : " + MainActivity.valeur + " °C");

        }


        Button retourMENUE = findViewById(R.id.retourMENUE);
        retourMENUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(temperature.this, MenuOnClick.class));
                finish();
            }
        });

        res = findViewById(R.id.textView3);
        Button ONtemp = findViewById(R.id.ONtemp);
        ONtemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{ONtemp}",null,null);
                res.setText("capteur temperature activée");
                findViewById(R.id.ONtemp).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFtemp).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFtemp = findViewById(R.id.OFFtemp);
        OFFtemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{OFFtemp}",null,null);
                res.setText("capteur temperature desactivée");
                findViewById(R.id.ONtemp).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFtemp).setBackgroundColor(Color.RED);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),MenuOnClick.class);
        startActivity(intent);
        //Toast.makeText(this,"Appuyer sur RETOUR AU MENU PRCIPAL",Toast.LENGTH_LONG).show();
    }


}

