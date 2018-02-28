package com.example.administrator.ebols.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.ebols.DB.TableData.ArchiveTable;
import com.example.administrator.ebols.DB.TableData.AssignedDriverTable;
import com.example.administrator.ebols.DB.TableData.BilledTable;
import com.example.administrator.ebols.DB.TableData.CompanyTable;
import com.example.administrator.ebols.DB.TableData.DeliveredTable;
import com.example.administrator.ebols.DB.TableData.DriverTable;
import com.example.administrator.ebols.DB.TableData.MemberTable;
import com.example.administrator.ebols.DB.TableData.NewLoadTable;
import com.example.administrator.ebols.DB.TableData.OrderTable;
import com.example.administrator.ebols.DB.TableData.PaidTable;
import com.example.administrator.ebols.DB.TableData.PickUpTable;
import com.example.administrator.ebols.DB.TableData.RefreshTable;
import com.example.administrator.ebols.DB.TableData.RolesTable;

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
    private OrderTable orderTable;
    private DriverTable driverTable;
    private AssignedDriverTable assignedDriverTable;
    private NewLoadTable newLoadTable;
    private PickUpTable pickUpTable;
    private ArchiveTable archiveTable;
    private PaidTable paidTable;
    private BilledTable billedTable;
    private DeliveredTable deliveredTable;
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        newLoadTable = new NewLoadTable();
        assignedDriverTable = new AssignedDriverTable();
        deliveredTable = new DeliveredTable();
        billedTable = new BilledTable();
        paidTable = new PaidTable();
        archiveTable = new ArchiveTable();
        pickUpTable = new PickUpTable();
        rolesTable = new RolesTable();
        memberTable = new MemberTable();
        refreshTable = new RefreshTable();
        companyTable = new CompanyTable();
        driverTable = new DriverTable();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        memberTable.CreateMemberTable(sqLiteDatabase);
        newLoadTable.CreateOrderTable(sqLiteDatabase);
        pickUpTable.CreateOrderTable(sqLiteDatabase);
        archiveTable.CreateOrderTable(sqLiteDatabase);
        paidTable.CreateOrderTable(sqLiteDatabase);
        billedTable.CreateOrderTable(sqLiteDatabase);
        deliveredTable.CreateOrderTable(sqLiteDatabase);
        assignedDriverTable.CreateOrderTable(sqLiteDatabase);
        companyTable.CreateCompanyTable(sqLiteDatabase);
        refreshTable.CreateRefreshTable(sqLiteDatabase);
        rolesTable.createRoleTable(sqLiteDatabase);
        driverTable.CreateDriverTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        memberTable.DropMemberTable(sqLiteDatabase);
        companyTable.DropCompanyTable(sqLiteDatabase);
        assignedDriverTable.DropOrderTable(sqLiteDatabase);
        pickUpTable.DropOrderTable(sqLiteDatabase);
        archiveTable.DropOrderTable(sqLiteDatabase);
        paidTable.DropOrderTable(sqLiteDatabase);
        billedTable.DropOrderTable(sqLiteDatabase);
        deliveredTable.DropOrderTable(sqLiteDatabase);
        newLoadTable.DropOrderTable(sqLiteDatabase);
        rolesTable.DropRolesTable(sqLiteDatabase);
        driverTable.DropDriverTable(sqLiteDatabase);
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
    public void insertData(List<Map<String, Object>> list, String tab){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if(tab.equals("assigned")){
            assignedDriverTable.DropOrderTable(sqLiteDatabase);
            assignedDriverTable.CreateOrderTable(sqLiteDatabase);
            assignedDriverTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("newLoad")){
            newLoadTable.DropOrderTable(sqLiteDatabase);
            newLoadTable.CreateOrderTable(sqLiteDatabase);
            newLoadTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("delivered")){
            deliveredTable.DropOrderTable(sqLiteDatabase);
            deliveredTable.CreateOrderTable(sqLiteDatabase);
            deliveredTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("billed")){
            billedTable.DropOrderTable(sqLiteDatabase);
            billedTable.CreateOrderTable(sqLiteDatabase);
            billedTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("paid")){
            paidTable.DropOrderTable(sqLiteDatabase);
            paidTable.CreateOrderTable(sqLiteDatabase);
            paidTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("archived")){
            archiveTable.DropOrderTable(sqLiteDatabase);
            archiveTable.CreateOrderTable(sqLiteDatabase);
            archiveTable.InsertData(list, sqLiteDatabase);
        }else if(tab.equals("pickedUp")){
            pickUpTable.DropOrderTable(sqLiteDatabase);
            pickUpTable.CreateOrderTable(sqLiteDatabase);
            pickUpTable.InsertData(list, sqLiteDatabase);
        }
    }

    public void insertData(Map<String, Object> map, int choose){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if(choose == 1){
            newLoadTable.InsertData(map, sqLiteDatabase);
        }else if(choose == 2){
            driverTable.InsertDriverTable(map, sqLiteDatabase);
        }else if(choose == 3){
            pickUpTable.InsertData(map, sqLiteDatabase);
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


    public Cursor getOrderTable(String tab){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        if(tab.equals("assigned")){
            cursor = assignedDriverTable.SelectData("*", sqLiteDatabase);
        }else if(tab.equals("newLoad")){
            cursor = newLoadTable.SelectData("*", sqLiteDatabase);
        }else if(tab.equals("delivered")){
            cursor = deliveredTable.SelectData("*", sqLiteDatabase);
        }else if(tab.equals("billed")){
            cursor = billedTable.SelectData("*", sqLiteDatabase);
        }else if(tab.equals("paid")){
            cursor = paidTable.SelectData("*", sqLiteDatabase);
        }else if(tab.equals("archived")){
            cursor = archiveTable.SelectData("*", sqLiteDatabase);
        }else{
            cursor = pickUpTable.SelectData("*", sqLiteDatabase);
        }
        return cursor;
    }

    public Cursor getOrderTable2(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = orderTable.SelectData2("*", sqLiteDatabase);
        return cursor;
    }

    public Cursor getDriverData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = driverTable.getKey_Driver_Data(sqLiteDatabase);
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
    public int getMemberId(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = memberTable.getMemberID(sqLiteDatabase);
        return result;
    }
}
