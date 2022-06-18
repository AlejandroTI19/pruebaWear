package com.example.pruebawear;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Button wButton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Intent intentLlamada;
    private Notification updateNotify;
    private NotificationCompat.Builder notification;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi_Canal";
    public static final int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "Without BigStyle, only a single line of text would be visible"+
            "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wButton = findViewById(R.id.wButton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();
        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                        //Intent para simular llamada con botón en notificación
                        intentLlamada = new Intent(MainActivity.this,MainActivity2.class);
                intentLlamada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent DialPendingIntent = PendingIntent.getActivity(MainActivity.this,0,intentLlamada,PendingIntent.FLAG_ONE_SHOT);
                        //start(view);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        String name = "Notification";
                        NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                        nm.createNotificationChannel(notificationChannel);

                        pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        int max_progress = 100;
                        int current_progress = 25;
                        notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Accion estandar")
                                .setContentText("Notificacion con acción estándar")
                                .setContentIntent(pendingIntent)
                                .addAction(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark, "Llamar", DialPendingIntent)
                                .setProgress(max_progress,current_progress,true);

                        nm.notify(idNotification, notification.build());

           /*     start(view);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notification";
                NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Mi notification")
                        .setContentText("La primera notificación")
                        .extend(wearableExtender);
                nm.notify(idNotification, notification.build());
            }

            public void start(View view){
                Timer t = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {


                String name = "Mi actualización";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                        nm.createNotificationChannel(notificationChannel);



                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Accion Estandar")
                                .setContentText("Notificacion con accion estandar")
                                .setContentIntent(pendingIntent);

                        nm.notify(idNotification, notification.build());*/


                       /* List<Notification> pages = new ArrayList<Notification>();
                        for (int i=1; i>3; i++){
                            Notification nt =
                          new NotificationCompat.Builder(MainActivity.this, idChannel)
                                    .setContentTitle("pagina" + i)
                                  .setContentText("Texto de la pagina" + 1)
                                  .build();
                            pages.add(nt);
                        }

                        NotificationCompat.WearableExtender extender = new NotificationCompat
                                .WearableExtender()
                                .addPages(pages);

                        Notification notificacion =
                                new NotificationCompat.Builder(MainActivity.this, idChannel)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("notificacion multipagina")
                                        .setContentText("esta es la primera pagina")
                                        .extend(extender)
                                        .build();*/



                       /* Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {

                                                public void run() {
                                                    updateNotify = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                                            .setSmallIcon(R.mipmap.ic_launcher)
                                                            .setContentTitle("notification")
                                                            .setContentText("Una actualizacion de Texto")
                                                            .build();
                                                    nm.notify(idNotification, updateNotify);
                                                }

                                            }, t.schedule(tt, 5000)*/
                    }
            });
        }
}