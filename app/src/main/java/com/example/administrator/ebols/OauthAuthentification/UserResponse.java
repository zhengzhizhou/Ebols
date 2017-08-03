package com.example.administrator.ebols.OauthAuthentification;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */

public class UserResponse {
    private List<Map<String, Object>> companies;
    private Map<String, Object> member;
    private String type;

    public List<Map<String, Object>> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Map<String, Object>> companies) {
        this.companies = companies;
    }

    public Map<String, Object> getMember() {
        return member;
    }

    public void setMember(Map<String, Object> member) {
        this.member = member;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
