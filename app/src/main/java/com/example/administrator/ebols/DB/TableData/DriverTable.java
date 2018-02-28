package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/5.
 */

public class DriverTable {
    public static final String Driver_Table_Name = "diver";
    public static final String Key_Diver_ID = "id";
    public static final String Key_Diver_Name = "name";
    private static final String Key_Diver_prefix = "prefix";
    private static final String Key_Diver_firstName = "firstName";
    private static final String Key_Diver_lastName = "lastName";
    private static final String Key_Diver_birthday = "birthday";
    private static final String Key_Diver_mobilePhone = "mobilePhone";
    private static final String Key_Diver_email = "email";
    private static final String Key_Diver_createdDate = "createdDate";
    private static final String Key_Diver_activatedDate = "activateDate";
    private static final String Key_Diver_blockedDate = "blockedDate";
    private static final String Key_Diver_type = "type";

    private static final String Key_Diver_Company_Id = "company_id";
    private static final String Key_Diver_Company_Name = "company_name";
    private static final String Key_Diver_Company_RegisterDate = "company_registerDate";
    private static final String Key_Diver_Company_Roles_SYSTEM_ADMIN = "SYSTEM_ADMIN";
    private static final String Key_Diver_Company_Roles_COMPANY_ADMIN = "COMPANY_ADMIN";
    private static final String Key_Diver_Company_Roles_COMPANY_DRIVER = "COMPANY_DRIVER";
    public static final String CREATE_DRIVER_TABLE="CREATE TABLE IF NOT EXISTS "
            +Driver_Table_Name+" ("
            +Key_Diver_ID+" INTEGER PRIMARY KEY, "
            +Key_Diver_Name+" VARCHAR NOT NULL," 
            +Key_Diver_prefix+" VARCHAR,"
            +Key_Diver_firstName+" VARCHAR, "
            +Key_Diver_lastName+" VARCHAR, "
            +Key_Diver_birthday+" VARCHAR, "
            +Key_Diver_mobilePhone+" VARCHAR, "
            +Key_Diver_email+" VARCHAR, "
            +Key_Diver_createdDate+" VARCHAR, "
            +Key_Diver_activatedDate+" VARCHAR, "
            +Key_Diver_blockedDate+" VARCHAR, "
            +Key_Diver_type+" VARCHAR, "
            +Key_Diver_Company_Id+" INTEGER, "
            +Key_Diver_Company_Name+" VARCHAR, "
            +Key_Diver_Company_RegisterDate+" VARCHAR, "
            +Key_Diver_Company_Roles_SYSTEM_ADMIN+" INTEGER, "
            +Key_Diver_Company_Roles_COMPANY_ADMIN+" INTEGER, "
            +Key_Diver_Company_Roles_COMPANY_DRIVER+" INTEGER);";

    private String INSERT_COMPANY_TABLE;
    private String SELECT_COMPANY_TABLE;

    public void CreateDriverTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_DRIVER_TABLE);
    }

    public void DropDriverTable(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Driver_Table_Name);
    }
    public void InsertDriverTable(Map<String, Object> map,  SQLiteDatabase sqLiteDatabase){

        INSERT_COMPANY_TABLE="INSERT INTO "+Driver_Table_Name+"(id, name,prefix,firstName, lastName, birthday, mobilePhone," +
                "email, createdDate, activateDate, blockedDate, type, company_id, company_name, company_registerDate, " +
                "SYSTEM_ADMIN, COMPANY_ADMIN, COMPANY_DRIVER) VALUES ('"
                +map.get("id")+"', '"+map.get("name")+"', '"+map.get("prefix")+"', '"+map.get("firstName")+"', '"+map.get("lastName")+"', '"+
                map.get("birthday")+ "', '"+map.get("mobilePhone")+"', '"+map.get("email")+"', '"+map.get("createdDate")+"', '"+
                map.get("activateDate")+"', '"+map.get("blockedDate")+ "', '"+map.get("type")+"', '"+map.get("company_id")+
                "', '"+map.get("company_name")+"', '"+map.get("company_registerDate")+"', '"+map.get("SYSTEM_ADMIN")+"', '"+map.get("COMPANY_ADMIN")
                +"', '"+map.get("COMPANY_DRIVER")+"');";
        sqLiteDatabase.execSQL(INSERT_COMPANY_TABLE);
    }

    public Cursor getKey_Driver_Data(SQLiteDatabase sqLiteDatabase){
        SELECT_COMPANY_TABLE = "SELECT * FROM "+Driver_Table_Name;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_COMPANY_TABLE,null);
        return cursor;
    }
}
