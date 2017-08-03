package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/25.
 */

public class CompanyTable {
    public static final String Company_Table_Name = "companies";
    public static final String Key_company_ID = "id";
    public static final String Key_company_Name = "name";

    public static final String CREATE_COMPANY_TABLE="CREATE TABLE IF NOT EXISTS "
            +Company_Table_Name+" ("
            +Key_company_ID+" INTEGER PRIMARY KEY, "
            +Key_company_Name+" VARCHAR NOT NULL);";

    private String INSERT_COMPANY_TABLE;
    private String SELECT_COMPANY_TABLE;

    public void CreateCompanyTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_COMPANY_TABLE);
    }

    public void DropCompanyTable(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Company_Table_Name);
    }
    public void InsertCompanyTable(String company_ID, String company_name, SQLiteDatabase sqLiteDatabase){

        INSERT_COMPANY_TABLE="INSERT INTO "+Company_Table_Name+"(id, name) VALUES ('"+company_ID+"', '"+company_name+"');";
        sqLiteDatabase.execSQL(INSERT_COMPANY_TABLE);
    }

    public String getKey_company_ID(SQLiteDatabase sqLiteDatabase){
        SELECT_COMPANY_TABLE = "SELECT "+Key_company_ID+" FROM "+Company_Table_Name;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_COMPANY_TABLE,null);
        cursor.moveToFirst();
        String companyId = cursor.getString(0);
        cursor.close();
        return companyId;
    }
}
