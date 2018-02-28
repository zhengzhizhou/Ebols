package com.example.administrator.ebols.Fragment;

import android.content.Context;
import android.database.Cursor;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.Object.ArchiveListObject;
import com.example.administrator.ebols.Object.AssignedListObject;
import com.example.administrator.ebols.Object.BilledListObject;
import com.example.administrator.ebols.Object.DelieveredListObject;
import com.example.administrator.ebols.Object.HomeListObject;
import com.example.administrator.ebols.Object.PaidListObject;
import com.example.administrator.ebols.Object.PickUpListObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class Initialize {
    private List<HomeListObject> homeListObjects;
    private List<AssignedListObject> assignedListObjects;
    private List<ArchiveListObject> archiveListObjects;
    private List<PickUpListObject> pickUpListObjects;
    private List<PaidListObject> paidListObjects;
    private List<DelieveredListObject> delieveredListObjects;
    private List<BilledListObject> billedListObjects;
    private DBHandler dbHandler;
    private OauthService oauthService;
    private String companyId, accessToken;
    private Cursor cursor_To, cursor_Origin, cursor_Destination;
    private OrdersRequest ordersRequest;
    private String tab;
    public Initialize( Context context, String tab){
        this.tab = tab;
        dbHandler = new DBHandler(context);
        companyId = dbHandler.getCompanyId();
        accessToken = "Bearer "+dbHandler.getAccessToken();
    }
    public void setHomeListObjects(List<HomeListObject> homeListObjects){
        this.homeListObjects = homeListObjects;
    }
    public void setAssignedListObjects(List<AssignedListObject> assignedListObjects){
        this.assignedListObjects = assignedListObjects;
    }

    public void setDelieveredListObjects(List<DelieveredListObject> delieveredListObjects) {
        this.delieveredListObjects = delieveredListObjects;
    }

    public void setPickUpListObjects(List<PickUpListObject> pickUpListObjects) {
        this.pickUpListObjects = pickUpListObjects;
    }

    public void setPaidListObjects(List<PaidListObject> paidListObjects) {
        this.paidListObjects = paidListObjects;
    }

    public void setArchiveListObjects(List<ArchiveListObject> archiveListObjects) {
        this.archiveListObjects = archiveListObjects;
    }

    public void setBilledListObjects(List<BilledListObject> billedListObjects) {
        this.billedListObjects = billedListObjects;
    }

    public List<HomeListObject> initializeNewOrder(){
        List<HomeListObject> list = new ArrayList<>();
        list = createNewOrder();
        return list;
    }

    public List<AssignedListObject> inializeAssigned(){
        List<AssignedListObject> list = new ArrayList<>();
        list = createAssignedList();
        return list;
    }

    public List<ArchiveListObject> inializeArchieved(){
        List<ArchiveListObject> list = new ArrayList<>();
        list = createArchivedList();
        return list;
    }

    private List<ArchiveListObject> createArchivedList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            archiveListObjects.add(new ArchiveListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return archiveListObjects;
    }

    public List<BilledListObject> inializeBilled(){
        List<BilledListObject> list = new ArrayList<>();
        list = createBilledList();
        return list;
    }

    private List<BilledListObject> createBilledList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            billedListObjects.add(new BilledListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return billedListObjects;
    }

    public List<PickUpListObject> inializePickedUp(){
        List<PickUpListObject> list = new ArrayList<>();
        list = createPickedupList();
        return list;
    }

    private List<PickUpListObject> createPickedupList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            pickUpListObjects.add(new PickUpListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return pickUpListObjects;
    }

    public List<DelieveredListObject> inializeDelievered(){
        List<DelieveredListObject> list = new ArrayList<>();
        list = createDelieveredList();
        return list;
    }

    private List<DelieveredListObject> createDelieveredList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            delieveredListObjects.add(new DelieveredListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return delieveredListObjects;
    }

    public List<PaidListObject> inializePaid(){
        List<PaidListObject> list = new ArrayList<>();
        list = createPaidList();
        return list;
    }

    private List<PaidListObject> createPaidList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            paidListObjects.add(new PaidListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return paidListObjects;
    }


    private List<AssignedListObject> createAssignedList() {
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, driverName;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(13) != null){
                driverName = cursor.getString(13).toString();
            }else{
                driverName = "N/A";
            }
            assignedListObjects.add(new AssignedListObject(id, companyId, itemAmount, driverName));
            cursor.moveToNext();
        }
        return assignedListObjects;
    }

    private List<HomeListObject> createNewOrder(){
        Cursor cursor = dbHandler.getOrderTable(tab);
        for(int i = 0; i<cursor.getCount(); i++){
            String id, companyId, itemAmount, start_address, start_city, start_zip, destination_address, destination_city, destination_zip;
            if(cursor.getString(1) != null){
                id = cursor.getString(1).toString();
            }else{
                id = "N/A";
            }
            if(cursor.getString(2) != null){
                companyId = cursor.getString(2).toString();
            }else{
                companyId = "N/A";
            }
            if(cursor.getString(3) != null){
                itemAmount = cursor.getString(3).toString();
            }else{
                itemAmount = "N/A";
            }
            if(cursor.getString(19) != null){
                start_address = cursor.getString(19).toString();
            }else{
                start_address = "";
            }
            if(cursor.getString(21) != null){
                start_city = cursor.getString(21).toString();
            }else{
                start_city = "";
            }
            if(cursor.getString(22) != null){
                start_zip = cursor.getString(22).toString();
            }else{
                start_zip = "";
            }
            if(cursor.getString(42) != null){
                destination_address = cursor.getString(42).toString();
            }else{
                destination_address = "";
            }
            if(cursor.getString(43) != null){
                destination_city = cursor.getString(43).toString();
            }else{
                destination_city = "";
            }
            if(cursor.getString(44) != null){
                destination_zip = cursor.getString(44).toString();
            }else{
                destination_zip = "";
            }
            homeListObjects.add(new HomeListObject(id, companyId, itemAmount, start_address + " "+ start_city + " "
                    + start_zip, destination_address + " " + destination_city + " " + destination_zip));
            cursor.moveToNext();
        }
        return homeListObjects;
    }

}
