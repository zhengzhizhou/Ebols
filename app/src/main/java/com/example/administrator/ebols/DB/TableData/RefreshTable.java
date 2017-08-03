package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Administrator on 2017/7/25.
 */

public class RefreshTable {
    public static final String Key_ACCESS_TOKEN = "access_token";
    public static final String Key_REFRESH_TOKEN="refresh_token";
    public static final String Refresh_Token_Table_Name="refreshToken";

    public static final String CREATE_REFRESH_TOKEN_TABLE="CREATE TABLE IF NOT EXISTS "
            +Refresh_Token_Table_Name +" (ID INTEGER PRIMARY KEY, "
            +Key_REFRESH_TOKEN+" VARCHAR NOT NULL, "
            +Key_ACCESS_TOKEN+" VARCHAR NOT NULL);";

    private String SELECT_REFRESH_TOKEN_TABLE;
    private String INSERT_REFRESH_TOKEN_TABLE;
    private String UPDATE_REFRESH_TOKEN_TABLE;


    public void CreateRefreshTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_REFRESH_TOKEN_TABLE);

    }
    public void UpdateRefreshTable(String refreshToken, String accessToken, SQLiteDatabase sqLiteDatabase){
        INSERT_REFRESH_TOKEN_TABLE="INSERT INTO refreshToken("+Key_REFRESH_TOKEN+","+Key_ACCESS_TOKEN+") VALUES ('"+refreshToken+"','"+accessToken+"');";
        SELECT_REFRESH_TOKEN_TABLE="SELECT * FROM refreshToken where ID = 1";
        UPDATE_REFRESH_TOKEN_TABLE="UPDATE refreshToken set "+Key_REFRESH_TOKEN+"='"+refreshToken+"',"+Key_ACCESS_TOKEN+"='"+accessToken+"' where ID = 1";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_REFRESH_TOKEN_TABLE, null);
        if(cursor.getCount()==0){
            sqLiteDatabase.execSQL(INSERT_REFRESH_TOKEN_TABLE);
        }else{
            sqLiteDatabase.execSQL(UPDATE_REFRESH_TOKEN_TABLE);
        }
        cursor.close();
    }

    public String getKey_Refresh_Token(SQLiteDatabase sqLiteDatabase){
        String refresh_token;
        SELECT_REFRESH_TOKEN_TABLE="SELECT * FROM "+Refresh_Token_Table_Name;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_REFRESH_TOKEN_TABLE, null);
        cursor.moveToFirst();
        refresh_token = cursor.getString(1);
        cursor.close();
        return refresh_token;
    }

    public String getKey_ACCESS_TOKEN(SQLiteDatabase sqLiteDatabase){
        SELECT_REFRESH_TOKEN_TABLE = "SELECT "+Key_ACCESS_TOKEN+" FROM "+Refresh_Token_Table_Name;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_REFRESH_TOKEN_TABLE, null);
        cursor.moveToFirst();
        String accessToken = cursor.getString(0);
        cursor.close();
        return accessToken;
    }
}
