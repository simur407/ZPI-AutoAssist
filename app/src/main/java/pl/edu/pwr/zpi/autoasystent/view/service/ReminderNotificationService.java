package pl.edu.pwr.zpi.autoasystent.view.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import pl.edu.pwr.zpi.autoasystent.R;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-09
 */
public class ReminderNotificationService extends Service {

    public static final String ARGS_TITLE = "title";
    public static final String ARGS_DESCRIPTION = "description";

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.car_ico)
                .setContentTitle("Dziala")
                .setContentText("Tekst jakis")
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(ARGS_TITLE);
        String descr = bundle.getString(ARGS_DESCRIPTION);
        if(title == null || descr == null) {
            throw new IllegalArgumentException("No enough arguments passed in Bundle. Must be at least TITLE and DESCRIPTION!");
        }

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.car_ico)
                .setContentTitle(title)
                .setContentText(descr)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
        return START_STICKY;
    }
}
