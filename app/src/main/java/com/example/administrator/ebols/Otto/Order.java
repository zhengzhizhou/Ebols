package com.example.administrator.ebols.Otto;

import com.squareup.otto.Bus;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Order {
    public static Bus bus = new Bus();
    public static Bus getBus(){
        return bus;
    }
}
