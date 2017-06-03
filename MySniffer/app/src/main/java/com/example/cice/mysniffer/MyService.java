package com.example.cice.mysniffer;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyService extends NotificationListenerService {
    public MyService() {
    }

    private String bundle2string(Bundle bundle){
        String strdev=null;

        strdev="Inicio Bundle {";

        for(String key:bundle.keySet()){
            strdev=strdev+" "+key+bundle.get(key)+";";
        }


        strdev="} FIN Bundle";

        return strdev;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d("MENSAJE","Listener conectado");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.d("MENSAJE","Notificación eliminada");
        Log.d("MENSAJE","Paquete "+sbn.getPackageName());
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.d("MENSAJE","Notificación recibida");
        Log.d("MENSAJE","Paquete "+sbn.getPackageName());

        Notification notification=sbn.getNotification();
        Bundle bundle= notification.extras;

        String mensaje_notif=bundle2string(bundle);

        Log.d("MENSAJE",mensaje_notif);

    }
}
