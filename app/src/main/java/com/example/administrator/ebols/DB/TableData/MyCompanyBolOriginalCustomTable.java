package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/27.
 */

public class MyCompanyBolOriginalCustomTable{
    public static final String id = "id";
    public static final String name = "name";
    public static final String addressLines = "addressLines";
    public static final String addressCity = "addressCity";
    public static final String addressState = "addressState";
    public static final String addressZipcode = "addressZipcode";
    public static final String contact = "contact";
    public static final String phone = "phone";
    public static final String fax = "fax";
    public static final String email = "email";
    public static final String TableName = "MyCompanyBolOriginalCustomTable";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            +TableName+" ("
            +id+" DOUBLE, "
            +name+" VERCHAR, "
            +addressLines+" VERCHAR,"
            +addressCity+" VERCHAR,"
            +addressState+" VERCHAR,"
            +addressZipcode+" VERCHAR,"
            +contact+" VERCHAR,"
            +phone+" VERCHAR,"
            +fax+" VERCHAR,"
            +email+" VERCHAR);";

    public void CreateTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public void DropTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP IF EXISTS "+TableName);
    }

    public void InsertTable(List<Map<String, Object>> MyCompany, SQLiteDatabase sqLiteDatabase) {
        for(int i = 0; i<MyCompany.size(); i++){
            Map<String, Object> original = (Map<String, Object>) MyCompany.get(i).get("original");
            Map<String, Object> custom = (Map<String, Object>)original.get("customer");
            Double my_id = (Double) MyCompany.get(i).get(id);
            String my_name = custom.get(name).toString().replace("'", "\'");
            String my_addressLines = custom.get(addressLines).toString().replace("'", "\'");
            String my_addressCity = custom.get(addressCity).toString().replace("'", "\'");
            String my_addressState=custom.get(addressState).toString().replace("'", "\'");
            String my_addressZipcode=custom.get(addressZipcode).toString().replace("'", "\'");
            String my_contact = custom.get(contact).toString().replace("'", "\'");
            String my_phone = custom.get(phone).toString().replace("'", "\'");
            String my_fax = custom.get(fax).toString().replace("'", "\'");
            String my_email = custom.get(email).toString().replace("'", "\'");
            String qry = "INSERT INTO "+TableName+"('"+id+"','"+name+"', '"+addressLines+"', '"+addressCity+"', '"+addressState+"', '"
                    +addressZipcode+"', '"+contact+"', '"+phone+"', '"+fax+"', '"+email+"') VALUES ('"+my_id+"', '"
                    +my_name+"', '"+my_addressLines+"', '"+my_addressCity+"', '"+my_addressState+"', '"+my_addressZipcode+"', '"
                    +my_contact+"', '"+my_phone+"', '"+my_fax+"', '"+my_email+"');";
            sqLiteDatabase.execSQL(qry);
        }


    }

    public Cursor SelectTable(String target, SQLiteDatabase sqLiteDatabase) {
        String qry = "SELECT "+target+" FROM "+TableName;
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        cursor.moveToFirst();
        return cursor;
    }


}
