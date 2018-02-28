package com.example.administrator.ebols.Object;

import android.widget.Spinner;

/**
 * Created by Administrator on 2017/7/7.
 */

public class HomeListObject {
    public String companyId;
    public String itemAmount;
    public String startPoint;
    public String destination;
    public String list_id;

    public HomeListObject(String list_id, String companyId, String itemAmount, String startPoint, String destination){
        this.list_id = list_id;
        this.companyId = companyId;
        this.itemAmount = itemAmount;
        this.destination = destination;
        this.startPoint = startPoint;
    }
}
