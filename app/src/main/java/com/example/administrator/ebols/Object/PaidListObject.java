package com.example.administrator.ebols.Object;

/**
 * Created by Administrator on 2017/10/11.
 */

public class PaidListObject {

    public String companyId;
    public String itemAmount;
    public String startPoint;
    public String destination;
    public String list_id;

    public PaidListObject(String list_id, String companyId, String itemAmount, String startPoint, String destination){
        this.list_id = list_id;
        this.companyId = companyId;
        this.itemAmount = itemAmount;
        this.destination = destination;
        this.startPoint = startPoint;
    }
}
