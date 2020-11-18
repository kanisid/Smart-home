package com.example.h_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BruitOnClick extends AppCompatActivity {


    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bruit_on_click);
        if((MainActivity.type.equals("alerte intrusion"))&&(MainActivity.etat.equals("1")))
        {
            findViewById(R.id.ONbruit).setBackgroundColor(Color.GREEN);
            findViewById(R.id.OFFbruit).setBackgroundColor(Color.WHITE);
            TextView textViewbruit = (TextView) findViewById(R.id.textViewbruit);
            textViewbruit.setText("Presence  detectée");
        }
        else
        {
            findViewById(R.id.ONbruit).setBackgroundColor(Color.WHITE);
            findViewById(R.id.OFFbruit).setBackgroundColor(Color.RED);
            TextView textViewbruit = (TextView) findViewById(R.id.textViewbruit);
            textViewbruit.setText("calme!");
        }

        Button retourMENUE = findViewById(R.id.retourMENUE);
        retourMENUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BruitOnClick.this, MenuOnClick.class));
                finish();
            }
        });

        res = findViewById(R.id.textView3);
        Button ONbruit = findViewById(R.id.ONbruit);
        ONbruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.ONbruit).setBackgroundColor(Color.BLUE);
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{ONbruit}",null,null);
                res.setText("capteur allumé");
                findViewById(R.id.ONbruit).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFbruit).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFbruit = findViewById(R.id.OFFbruit);
        OFFbruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{OFFbruit}",null,null);
                res.setText("capteur éteint");
                findViewById(R.id.ONbruit).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFbruit).setBackgroundColor(Color.RED);
            }
        });


    }
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),MenuOnClick.class);
        startActivity(intent);
        // Toast.makeText(this,"Appuyer sur RETOUR AU MENU PRCIPAL",Toast.LENGTH_LONG).show();

    }

}