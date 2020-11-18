package com.example.h_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.h_house.notification.CHANNEL_1_ID;
import static com.example.h_house.notification.CHANNEL_2_ID;
import static com.example.h_house.notification.CHANNEL_3_ID;

public class MainActivity extends AppCompatActivity implements MessageListener  {
    private  static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS=0;
    private  static final int MY_PERMISSIONS_REQUEST_SEND_SMS=1;
    public static String type;
    public static String valeur;
    public static String etat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReceiveSMS.bindListener(this);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }
        SmsManager.getDefault().sendTextMessage("+33668949532",null,"\n{Bienvenue}",null,null);
/*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            SmsManager.getDefault().sendTextMessage("+33651267665",null,"\n{Bienvenue1}",null,null);

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
            {
                SmsManager.getDefault().sendTextMessage("+33651267665",null,"\n{Bienvenue}",null,null);

                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
                {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECEIVE_SMS))
                    {
                        SmsManager.getDefault().sendTextMessage("+33651267665",null,"\n{Bienvenue2}",null,null);

                    }
                    else
                    {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS );
                    }
                }
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

 */



    }
/*
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS :
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    SmsManager.getDefault().sendTextMessage("+33651267665",null,"\n{Bienvenue}",null,null);
                    Toast.makeText(this,"Accès au messages accordé!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"les fonctionalités de l'application sont reduites!!",Toast.LENGTH_LONG).show();

                }
            }
            case  MY_PERMISSIONS_REQUEST_SEND_SMS :

            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"envoie de message accepté!!",Toast.LENGTH_LONG).show();
                    SmsManager.getDefault().sendTextMessage("+33651267665",null,"\n{Bienvenue4}",null,null);

                }
                else
                {
                    Toast.makeText(this,"les fonctionalités sont reduites!!",Toast.LENGTH_LONG).show();

                }
            }

        }
    }

 */

    public void messageReceived(String num, String message) {


        if (num.equals("+33668949532")) {

            type = message.substring(message.indexOf("{") + 1, message.indexOf("="));
            valeur = message.substring(message.indexOf("=") + 1, message.indexOf("#"));
            etat = message.substring(message.indexOf("#") + 1, message.indexOf("}"));
           if(type.equals("Bienvenue")){
               //startActivity(new Intent(MainActivity.this, MenuOnClick.class));
               Runnable runnable = new Runnable() {
                   @Override
                   public void run() {
                       Intent intent = new Intent(getApplicationContext(),MenuOnClick.class);
                       startActivity(intent);
                       finish();
                   }
               };
               new Handler().postDelayed(runnable, 100);

           }
           else if (type.equals("temperature")) {
               Intent intent = new Intent(getApplicationContext(),temperature.class);
               startActivity(intent);
               if (etat.equals("1")) {

                   if (Integer.parseInt(valeur) >= 30) {
                       NotificationManagerCompat notificationManager;

                       notificationManager = NotificationManagerCompat.from(this);

                       Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                               .setSmallIcon(R.drawable.ic_message)
                               .setContentTitle("Alerte Température")
                               .setContentText("Temperature " + valeur + "°C")
                               .setVibrate(new long[]{0, 250, 100, 250})
                               .setSound(RingtoneManager.getActualDefaultRingtoneUri(this, R.raw.notifsound))
                               .setPriority(NotificationCompat.PRIORITY_HIGH)
                               .setCategory(NotificationCompat.CATEGORY_ALARM)
                               .build();

                       notificationManager.notify(1, notification);
                   }

               }

           }

             else if (type.equals("alerte gaz")) {
               //Intent intent = new Intent(getApplicationContext(),FumeeOnClick.class);
              // startActivity(intent);
                if (etat.equals("1")) {

                    NotificationManagerCompat notificationManager;

                    notificationManager = NotificationManagerCompat.from(this);

                    Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                            .setSmallIcon(R.drawable.ic_message)
                            .setContentTitle("Alerte gaz")
                            .setContentText("presence de gaz detectée")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_ALARM)
                            .build();

                    notificationManager.notify(1, notification);


                }
            } else if (type.equals("alerte intrusion")) {
              // Intent intent = new Intent(getApplicationContext(),BruitOnClick.class);
               //startActivity(intent);
                if (etat.equals("1")) {

                    NotificationManagerCompat notificationManager;

                    notificationManager = NotificationManagerCompat.from(this);

                    Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                            .setSmallIcon(R.drawable.ic_message)
                            .setContentTitle("Alerte intrusion")
                            .setContentText("presence de mouvement detectée")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_ALARM)
                            .build();

                    notificationManager.notify(1, notification);

                }
            }
        }
    }




}

