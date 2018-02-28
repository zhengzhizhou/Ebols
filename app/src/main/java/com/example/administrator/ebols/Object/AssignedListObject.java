package com.example.administrator.ebols.Object;

/**
 * Created by Administrator on 2017/7/7.
 */

public class AssignedListObject {
    public String companyId;
    public String itemAmount;
    public String driverName;
    public String list_id;

    public AssignedListObject(String list_id, String companyId, String itemAmount, String driverName){
        this.list_id = list_id;
        this.companyId = companyId;
        this.itemAmount = itemAmount;
        this.driverName = driverName;
    }
}
