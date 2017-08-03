package com.example.administrator.ebols.Object;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/7/3.
 */

public class mTimer {
    private Timer timer;
    public mTimer(int seconds){
        timer=new Timer();
        timer.schedule(new timerTask(), seconds*1000 );
    }
    private class timerTask extends TimerTask{

        @Override
        public void run() {
            timer.cancel();
        }
    }
}
