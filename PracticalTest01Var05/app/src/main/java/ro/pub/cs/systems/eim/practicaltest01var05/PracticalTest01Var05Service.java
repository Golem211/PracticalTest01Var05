package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

public class PracticalTest01Var05Service extends Service {

    public static final String ACTION_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var05.broadcast";
    private static final int BROADCAST_INTERVAL = 5000; // 5 seconds
    private Handler handler;
    private Runnable broadcastRunnable;
    private String[] templateElements;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        templateElements = intent.getStringArrayExtra("templateElements");
        if (templateElements != null) {
            broadcastRunnable = new Runnable() {
                int index = 0;

                @Override
                public void run() {
                    if (index < templateElements.length) {
                        Intent broadcastIntent = new Intent(ACTION_BROADCAST);
                        broadcastIntent.putExtra("element", templateElements[index]);
                        sendBroadcast(broadcastIntent);
                        index++;
                        handler.postDelayed(this, BROADCAST_INTERVAL);
                    }
                }
            };
            handler.post(broadcastRunnable);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(broadcastRunnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}