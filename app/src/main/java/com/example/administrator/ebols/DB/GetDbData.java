package com.example.administrator.ebols.DB;

import android.database.Cursor;

/**
 * Created by Administrator on 2017/10/10.
 */

public class GetDbData {
    private DBHandler dbHandler;
    private String list_id;
    public GetDbData(DBHandler dbHandler, String list_id){
        this.dbHandler = dbHandler;
        this.list_id = list_id;
    }
    public Cursor getDbData(String mTab) {
        Cursor cursor = dbHandler.getOrderTable(mTab);
        cursor.moveToFirst();
        while(!(cursor.getString(1).toString().equals(list_id))){
            cursor.moveToNext();
        }
        return cursor;
    }

}
