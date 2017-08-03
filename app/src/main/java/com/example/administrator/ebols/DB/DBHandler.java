package com.example.administrator.ebols.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.ebols.DB.TableData.CompanyTable;
import com.example.administrator.ebols.DB.TableData.MemberTable;
import com.example.administrator.ebols.DB.TableData.MyCompanyBolDestinationCustomTable;
import com.example.administrator.ebols.DB.TableData.MyCompanyBolOriginalCustomTable;
import com.example.administrator.ebols.DB.TableData.MyCompanyBolTOTable;
import com.example.administrator.ebols.DB.TableData.RefreshTable;
import com.example.administrator.ebols.DB.TableData.RolesTable;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */

public class DBHandler extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "myDatabaseForTest";
    public static final int DATABASE_VERSION = 3;

    private RolesTable rolesTable;
    private RefreshTable refreshTable;
    private CompanyTable companyTable;
    private MemberTable memberTable;
    private MyCompanyBolTOTable myCompanyBolTOTable;
    private MyCompanyBolDestinationCustomTable myCompanyBolDestinationCustomTable;
    private MyCompanyBolOriginalCustomTable myCompanyBolOriginalCustomTable;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        rolesTable = new RolesTable();
        memberTable = new MemberTable();
        refreshTable = new RefreshTable();
        companyTable = new CompanyTable();
        myCompanyBolDestinationCustomTable = new MyCompanyBolDestinationCustomTable();
        myCompanyBolOriginalCustomTable = new MyCompanyBolOriginalCustomTable();
        myCompanyBolTOTable = new MyCompanyBolTOTable();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        memberTable.CreateMemberTable(sqLiteDatabase);
        companyTable.CreateCompanyTable(sqLiteDatabase);
        refreshTable.CreateRefreshTable(sqLiteDatabase);
        rolesTable.createRoleTable(sqLiteDatabase);
        myCompanyBolDestinationCustomTable.CreateTable(sqLiteDatabase);
        myCompanyBolOriginalCustomTable.CreateTable(sqLiteDatabase);
        myCompanyBolTOTable.create_MyCompanyBolTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        memberTable.DropMemberTable(sqLiteDatabase);
        companyTable.DropCompanyTable(sqLiteDatabase);
        rolesTable.DropRolesTable(sqLiteDatabase);
        myCompanyBolTOTable.DropMyCompanyBolTable(sqLiteDatabase);
        myCompanyBolOriginalCustomTable.DropTable(sqLiteDatabase);
        myCompanyBolDestinationCustomTable.DropTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String ID, String name, int choose/*0 for member, 1 for company*/){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if(choose==0){
            memberTable.InsertMemberTable(ID, name, sqLiteDatabase);
        }else if(choose == 1) {
            companyTable.InsertCompanyTable(ID, name, sqLiteDatabase);
        }
    }
    public void insertData(List<Map<String, Object>> MyCompany, int choose){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if(choose == 0){
            myCompanyBolTOTable.insertData(MyCompany, sqLiteDatabase);
        }else if(choose ==1){
            myCompanyBolDestinationCustomTable.InsertTable(MyCompany, sqLiteDatabase);
        }else if(choose == 2){
            myCompanyBolOriginalCustomTable.InsertTable(MyCompany, sqLiteDatabase);
        }

    }

    public void insertData(String user_ID, List<String> company_Roles){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        rolesTable.insertRolesTable(user_ID, company_Roles, sqLiteDatabase);
    }


    public String getCompanyId(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String companyId = companyTable.getKey_company_ID(sqLiteDatabase);
        return companyId;
    }

    public String getAccessToken(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String accessToken = refreshTable.getKey_ACCESS_TOKEN(sqLiteDatabase);
        return accessToken;
    }

    public Cursor getMyCompanyBolData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = myCompanyBolTOTable.getDate("*", sqLiteDatabase);
        return cursor;
    }

    public Cursor getMyCompanyBolOriginalData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        cursor = myCompanyBolOriginalCustomTable.SelectTable("*", sqLiteDatabase);
        return cursor;
    }
    public Cursor getMyCompanyBolDestinationData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        cursor = myCompanyBolDestinationCustomTable.SelectTable("*", sqLiteDatabase);
        return cursor;

    }
    public void updataRefreshToken(String refreshToken, String accessToken){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        refreshTable.UpdateRefreshTable(refreshToken, accessToken, sqLiteDatabase);
    }

    public String getKey_Refresh_Token(){
        String refresh_token;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        refresh_token = refreshTable.getKey_Refresh_Token(sqLiteDatabase);
        return refresh_token;
    }

    public int getMemberData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = memberTable.getMemberCount(sqLiteDatabase);
        return num;
    }
    public String getMembemData(String data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String result = memberTable.getMemberData(data, sqLiteDatabase);
        return result;
    }
}
