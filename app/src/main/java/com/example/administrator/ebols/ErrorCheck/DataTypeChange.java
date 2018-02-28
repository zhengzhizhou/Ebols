package com.example.administrator.ebols.ErrorCheck;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/27.
 */

public class DataTypeChange {
    private String input;
    DataTypeCheck dataTypeCheck;
    public DataTypeChange(String input){
        this.input = input;
        dataTypeCheck = new DataTypeCheck(input);
    }

    public int ChangeToInt(String input){
        int result = Integer.parseInt(input);
        return result;
    }

    public double ChangeToDouble(String input){
        double result = Double.parseDouble(input);
        return result;
    }

}
