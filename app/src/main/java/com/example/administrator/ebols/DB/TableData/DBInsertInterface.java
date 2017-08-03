package com.example.administrator.ebols.DB.TableData;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/8/2.
 */

public interface DBInsertInterface {
    public void insertData();
    public void insertData(Object object, SQLiteDatabase sqLiteDatabase);
}
