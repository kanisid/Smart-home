package com.example.h_house;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class notification extends Application {
    public static final String CHANNEL_1_ID = "Température";
    public static final String CHANNEL_2_ID = "fumée";
    public static final String CHANNEL_3_ID = "bruit";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    public void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Temperature",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription(" Channel température");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Fumée",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription(" Channel fumée");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "bruit",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription(" Channel bruit");



            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);


        }
    }
}
