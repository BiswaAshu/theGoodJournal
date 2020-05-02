package syndicatestudios.thegoodjournal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Ashutosh on 09-07-2019.
 */

public class BirthdayNotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder nb=new NotificationCompat.Builder(context);
        nb.setContentTitle("Happy Birthday");
        nb.setContentText("Wish you a very happy Birthday, have a awesome day.");
        nb.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI);
        nb.setSmallIcon(R.drawable.appicon1);
        nb.setOngoing(false);
        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            final String NOTIFICATION_CHANNEL_ID = "10";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            nb.setChannelId(NOTIFICATION_CHANNEL_ID);
            nm.createNotificationChannel(notificationChannel);
        }
        nm.notify(10, nb.build());
    }
}
