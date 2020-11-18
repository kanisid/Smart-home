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

public class PorteOnClick extends AppCompatActivity {

    TextView res1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porte_on_click);

        Button retourMENUE = findViewById(R.id.retourMENUE);
        retourMENUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PorteOnClick.this, MenuOnClick.class));

                finish();
            }
        });

        res1 = findViewById(R.id.textView3);
        Button ouvrir = findViewById(R.id.ouvrir);
        ouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{garage-ouvrir}",null,null);
                res1.setText("Porte garage ouverte");
                findViewById(R.id.ouvrir).setBackgroundColor(Color.GREEN);
                findViewById(R.id.fermer).setBackgroundColor(Color.WHITE);
            }
        });

        Button fermer = findViewById(R.id.fermer);
        fermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{garage-fermer}",null,null);
                res1.setText("Porte garage fermer");
                findViewById(R.id.ouvrir).setBackgroundColor(Color.WHITE);
                findViewById(R.id.fermer).setBackgroundColor(Color.RED);
            }
        });


    }
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),MenuOnClick.class);
        startActivity(intent);
        //Toast.makeText(this,"Appuyer sur RETOUR AU MENU PRCIPAL",Toast.LENGTH_LONG).show();
    }
}

