package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MemberTable {
    public static final String Member_Table_Name = "member";
    public static final String Key_ID = "id";
    public static final String Key_USERNAME = "name";

    public static final String CREATE_MEMBER_TABLE="CREATE TABLE "
            +Member_Table_Name+" ("
            +Key_ID+" INTEGER PRIMARY KEY, "
            +Key_USERNAME+" VARCHAR NOT NULL);";

    private String INSERT_MEMBER_TABLE;
    private String SELECT_MEMBER_TABLE;

    public void CreateMemberTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_MEMBER_TABLE);
    }

    public void DropMemberTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Member_Table_Name);
    }

    public void InsertMemberTable(String user_ID, String username, SQLiteDatabase sqLiteDatabase){
        INSERT_MEMBER_TABLE="INSERT INTO MEMBER(id, name) VALUES ('"+user_ID+"','"+username+"');";
        sqLiteDatabase.execSQL(INSERT_MEMBER_TABLE);
    }

    public int getMemberCount(SQLiteDatabase sqLiteDatabase){
        SELECT_MEMBER_TABLE="SELECT ID FROM MEMBER";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_MEMBER_TABLE, null);
        int num = cursor.getCount();
        cursor.close();
        return num;
    }
    public String getMemberData(String data, SQLiteDatabase sqLiteDatabase){
        SELECT_MEMBER_TABLE="SELECT "+data+" FROM MEMBER";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_MEMBER_TABLE, null);
        String result = cursor.getString(1);
        cursor.close();
        return result;
    }
}
