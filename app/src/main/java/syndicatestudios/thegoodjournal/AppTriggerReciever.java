package syndicatestudios.thegoodjournal;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Ashutosh on 06-07-2019.
 */

public class AppTriggerReciever extends BroadcastReceiver {
    Intent i;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "app reciever triggered", Toast.LENGTH_SHORT).show();
        i = new Intent(context, WorkingActivity.class);
        PendingIntent p2=getActivity(context,1001,i,PendingIntent.FLAG_UPDATE_CURRENT);
        context.startActivity(i);
    }
}
