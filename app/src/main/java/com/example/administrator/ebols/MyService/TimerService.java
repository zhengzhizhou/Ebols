package com.example.administrator.ebols.MyService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.administrator.ebols.RetrofitClass.RefreshAccess;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/10/2.
 */

public class TimerService extends Service{

    private static Timer timer = new Timer();
    private Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        startService();
        return Service.START_NOT_STICKY;
    }

    private void startService(){
        timer.scheduleAtFixedRate(new timertask(), 0, 1000*3500);
    }

    private class timertask extends TimerTask {
        @Override
        public void run() {
            new RefreshAccess(context).run();
            toastHandler.sendEmptyMessage(0);
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(context, "destroy", Toast.LENGTH_LONG).show();
    }
    private final Handler toastHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();;
        }
    };
}
