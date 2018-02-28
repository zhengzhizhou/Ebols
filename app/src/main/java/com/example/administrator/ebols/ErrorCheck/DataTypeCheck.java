package com.example.administrator.ebols.ErrorCheck;

/**
 * Created by Administrator on 2017/11/27.
 */

public class DataTypeCheck {
    private String input;
    public DataTypeCheck(String input){
        this.input = input;
    }
    public boolean isInteger(){
        if(input==null){
            return false;
        }
        int len = input.length();
        if(len==0){
            return false;
        }
        int i = 0;
        if(input.charAt(0)=='-'){
            if(len==1){
                return false;
            }
            i = 1;
        }
        for(; i<len; i++){
            char c = input.charAt(i);
            if(c<'0'||c>'9'){
                return false;
            }
        }
        return true;
    }

    public boolean isDouble(){
        String pattern = "[0-9]+/.[0-9]*";
        if(input==null){
            return false;
        }
        int len = input.length();
        if(len==0){
            return false;
        }
        if(!input.matches(pattern)){
            return false;
        }
        return true;
    }
}
