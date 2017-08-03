package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2017/7/27.
 */

public class MyCompanyBolTOTable {
    public static final String MyCompanyBolTable = "MyCompanyBolTo";
    public static final String id = "id";
    public static final String orderID = "orderId";
    public static final String companyId = "companyId";
    public static final String number = "number";
    public static final String status = "status";
    public static final String billClient = "billClient";
    public static final String archivedDate = "archivedDate";
    public static final String instruction = "instruction";
    public static final String billedDate = "billedDate";
    public static final String paidDate = "paidDate";
    public static final String totalPrice = "totalPrice";
    public static final String modifiedDate="modifiedDate";
    public static final String CREATE_MY_COMPANY_BOL_TABLE = "CREATE TABLE IF NOT EXISTS "
            +MyCompanyBolTable+" ("
            +id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +orderID + " DOUBLE, "
            +companyId+" DOUBLE, "
            +number+" VERCHAR, "
            +status+" VERCHAR, "
            +billClient+" VERCHAR, "
            +archivedDate+" VERCHAR, "
            +instruction+" VERCHAR, "
            +billedDate+" VERCHAR, "
            +paidDate+" VERCHAR, "
            +totalPrice+" DOUBLE, "
            +modifiedDate+" DOUBLE);";
    public void create_MyCompanyBolTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_MY_COMPANY_BOL_TABLE);
    }

    public void DropMyCompanyBolTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP IF EXISTS "+MyCompanyBolTable);
    }

    public void insertData(List<Map<String, Object>> MyCompany, SQLiteDatabase sqLiteDatabase)
    {
        for(int i = 0; i<MyCompany.size(); i++){
            double my_orderId = (double)MyCompany.get(i).get("id");
            double my_companyId = (double)MyCompany.get(i).get("companyId");
            String my_number = (String)MyCompany.get(i).get("number");
            String my_status = (String)MyCompany.get(i).get("status");
            String my_billClient = (String)MyCompany.get(i).get("billClient");
            String my_archivedDate = (String)MyCompany.get(i).get("instruction");
            String my_instruction = (String)MyCompany.get(i).get("instruction");
            String my_billedDate = (String)MyCompany.get(i).get("billedDate");
            String my_paidDate = (String)MyCompany.get(i).get("paidDate");
            double my_totalPrice = (double)MyCompany.get(i).get("totalPrice");
            double my_modifiedDate = (double)MyCompany.get(i).get("modifiedDate");
            String qry = "INSERT INTO "+MyCompanyBolTable+"('"+orderID+"', '"+companyId+"','"+number+"', '"+status+"', '"+billClient+"', '"+archivedDate+"', '"
                    +instruction+"', '"+billedDate+"', '"+paidDate+"', '"+totalPrice+"', '"+modifiedDate+"') VALUES " +
                    "('"+my_orderId+"', '"+my_companyId+"','"+my_number+"', '"+my_status+"', '"+my_billClient+"', '"+my_archivedDate+"', '"+my_instruction+"', '"
                    +my_billedDate+"', '"+my_paidDate+"', '"+my_totalPrice+"', '"+my_modifiedDate+"');";
            sqLiteDatabase.execSQL(qry);
        }
    }
    public Cursor getDate(String target, SQLiteDatabase sqLiteDatabase){
        String qry = "SELECT "+target+" FROM "+MyCompanyBolTable;
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        cursor.moveToFirst();
        return cursor;
    }
}
