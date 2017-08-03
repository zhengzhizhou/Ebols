package com.example.administrator.ebols.Object;

import android.widget.Spinner;

/**
 * Created by Administrator on 2017/7/7.
 */

public class HomeListObject {
    public String Id;
    public String itemAmount;
    public String startPoint;
    public String destination;

    public HomeListObject(String Id, String itemAmount, String startPoint, String destination){
        this.Id = Id;
        this.itemAmount = itemAmount;
        this.destination = destination;
        this.startPoint = startPoint;
    }
}
