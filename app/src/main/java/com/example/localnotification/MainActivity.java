package com.example.localnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button send;

    public final String CHANNEL_ID ="1";

    int counter =0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter+=1;
                send.setText(""+counter);
                if(counter==8)
                {
                    sendLocalNotification();
                }

            }
        });
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    public void sendLocalNotification()
    {
        @SuppressLint({"NewApi", "LocalSuppress"}) NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"1"
        , NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        Notification.Builder builder = new Notification.Builder(MainActivity.this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Hello USER")
                .setContentText("Thank you for seeing these notification \n you will daily update acitivities.")
                .setPriority(Notification.PRIORITY_DEFAULT);
        NotificationManagerCompat compat = NotificationManagerCompat.from(MainActivity.this);
        compat.notify(1,builder.build());

    }
}