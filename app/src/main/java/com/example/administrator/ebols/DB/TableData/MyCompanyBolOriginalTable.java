package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/27.
 */

public class MyCompanyBolOriginalTable{
    public static final String date = "date";
    public static final String TableName = "original";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TableName+"(id INTEGER,"+date
            +" VERCHAR);";
    public void CreateTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    public void DropTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP IF EXISTS "+TableName);
    }

    public void InsertTable(Object object, SQLiteDatabase sqLiteDatabase) {

    }

    public Cursor SelectTable(String target, SQLiteDatabase sqLiteDatabase) {
        return null;
    }
}
