package com.example.administrator.ebols.OauthAuthentification;

import com.example.administrator.ebols.Fragment.SignUpFragments.OwnerFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/5.
 */

public class OwnerRegisterRequest {
    private Map<String, String> member;
    private Map<String, String> company;

    public OwnerRegisterRequest(Map<String, String> member, Map<String, String> company){
        this.member = member;
        this.company = company;
    }

}
