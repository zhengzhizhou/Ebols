package com.example.administrator.ebols.OauthAuthentification;

import com.example.administrator.ebols.DB.DBHandler;

/**
 * Created by Administrator on 2017/7/18.
 */

public class OrdersRequest {
    private String companyID;
    public OrdersRequest(String companyID){
        this.companyID = companyID;
    }

    public String getCompanyID() {
        return companyID;
    }

}
