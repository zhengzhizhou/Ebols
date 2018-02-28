package com.example.administrator.ebols.DB.TableData;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

public class RolesTable {

    private static final String Roles_Company_Table_Name = "roles";
    private static final String Key_ID = "id";
    private static final String Key_roles_Role = "role";
    private static final String CREATE_COMPANY_ROLES_TABLE="CREATE TABLE IF NOT EXISTS "
            +Roles_Company_Table_Name+" ("
            +Key_ID+" INTEGER, "
            +Key_roles_Role+" VARCHAR NOT NULL);";
    private String INSERT_ROLES_TABLE;

    public void createRoleTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_COMPANY_ROLES_TABLE);
    }
    public void DropRolesTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Roles_Company_Table_Name);
    }

    public void insertRolesTable(String user_ID, List<String> company_Roles, SQLiteDatabase sqLiteDatabase){
        for(int i = 0; i < company_Roles.size(); i++){
            String getKey_roles_Role = company_Roles.get(i);
            INSERT_ROLES_TABLE="INSERT INTO "+Roles_Company_Table_Name+"(id, role) VALUES ('"+user_ID+"', '"+getKey_roles_Role+"');";
            sqLiteDatabase.execSQL(INSERT_ROLES_TABLE);
        }
    }
}
