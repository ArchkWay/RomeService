package com.example.archek.romeservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.concurrent.TimeUnit;


import static com.example.archek.romeservice.App.CHANNEL_1_ID;

public class MyService extends Service {

    private NotificationManagerCompat notificationManager;
    int x;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = NotificationManagerCompat.from(this);
        for(x = 0; x <= 100; x = x + 5){
            sendOnChannel1(x);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(x == 100){
                x = 0;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");

    }

    public void sendOnChannel1(int x){
        String message = convert(x);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

    public static String convert(int input) {
        if (input < 1 )
            return "0";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

}
