package com.example.administrator.ebols.ErrorCheck;

/**
 * Created by Administrator on 2017/10/13.
 */

public class LoginErrorCheck {
    private String pass;
    private String account;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void AccountErrorCheck(String account){
        String pattern = "(.+)@(.+)&";
        
    }
}
