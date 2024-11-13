package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PracticalTest01Var05BroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PracticalTest01Var05Service.ACTION_BROADCAST)) {
            String element = intent.getStringExtra("element");
            Toast.makeText(context, "Received element: " + element, Toast.LENGTH_SHORT).show();
        }
    }
}