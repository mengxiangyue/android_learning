package io.mshare.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    private DownLoadBinder mBinder = new DownLoadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("mxy", "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("mxy", "onStartCommand");
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "service test";
            String channelName = "serevice Name";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this, channelId)
                    .setContentTitle("service test")
                    .setContentText("content text")
                    .setContentIntent(pi)

                    .build();
            notification.flags |= Notification.FLAG_NO_CLEAR;
            startForeground(1, notification);
        } else {
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("service test")
                    .setContentText("content text")
                    .setContentIntent(pi)
                    .build();
            notification.flags |= Notification.FLAG_NO_CLEAR;
            startForeground(1, notification);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("mxy", "onDestroy");
    }

    class DownLoadBinder extends Binder {
        public void startDownload() {
            Log.e("mxy", "startDownload");
        }

        public int getProgress() {
            Log.e("mxy", "getProgress");
            return 0;
        }
    }
}
