package com.example.administrator.ebols.OauthAuthentification;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/20.
 */

public class OrdersResponse {
    int total;
    List<Map<String, Object>> list;
    Map<String, Object> tabs;

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public Map<String, Object> getTabs() {
        return tabs;
    }

    public void setTabs(Map<String, Object> tabs) {
        this.tabs = tabs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
