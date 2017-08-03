package com.example.administrator.ebols.DB;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */

public class Session {
    public List<Map<String, Object>> companies;
    public Map<String, Object> member;
    public Session(){

    }

    public List<Map<String, Object>> getCompanies(){
        return companies;
    }

    public Map<String, Object> getMember(){
        return member;
    }

}
