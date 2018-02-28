package com.example.administrator.ebols.OauthAuthentification;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/5.
 */

public class DriverResponse {
    private List<Map<String, Object>> list;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

}
