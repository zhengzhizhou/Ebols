package com.example.administrator.ebols.DB.TableData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/6.
 */

public class PaidTable {
    private static final String Order_Table_Name = "Paid_Order";

    private static String CREATE_ORDER_TABLE;
    private static String DROP_ORDER_TABLE;
    private static String INSERT_ORDER_TABLE;
    public PaidTable(){
        CREATE_ORDER_TABLE="CREATE TABLE IF NOT EXISTS `"
                +Order_Table_Name+ "` ("+OrderConstant.Key_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+OrderConstant.Key_Order_ID+" double,"+OrderConstant.Key_Order_CompanyId+" double,"+OrderConstant.Key_Order_number+" VERCHAR,"+OrderConstant.Key_Order_status+" VERCHAR," +
                OrderConstant.Key_Order_billClient+" VERCHAR,"+OrderConstant.Key_Order_instruction+" VERCHAR,"+OrderConstant.Key_Order_totalPrice+" double,"+OrderConstant.Key_Order_modifiedDate+" double," +
                OrderConstant.Key_Order_ArchivedDate+" double,"+OrderConstant.Key_Order_BilledDate+" double,"+OrderConstant.Key_Order_PaidDate+" double,"+OrderConstant.Key_Order_Driver_id+" double, " +
                OrderConstant.Key_Order_Driver_name+" VERCHAR,"+OrderConstant.Key_Order_Driver_Prefix+" VERCHAR,"+OrderConstant.Key_Order_Driver_FirstName+" VERCHAR,"+OrderConstant.Key_Order_Driver_LastName+" VERCHAR," +
                OrderConstant.Key_Order_Original_Date+" double,"+OrderConstant.Key_Order_Original_Customer_Name+" VERCHAR,"+OrderConstant.Key_Order_Original_Customer_AddressLines+" VERCHAR," +
                OrderConstant.Key_Order_Original_Customer_AddressCity+" VERCHAR,"+OrderConstant.Key_Order_Original_Customer_AddressState+" VERCHAR,"+OrderConstant.Key_Order_Original_Customer_AddressZipCode+" VERCHAR," +
                OrderConstant.Key_Order_Original_Customer_Contact+" VERCHAR,"+OrderConstant.Key_Order_Original_Customer_Phone+" VERCHAR,"+OrderConstant.Key_Order_Original_Customer_fax+" VERCHAR," +
                OrderConstant.Key_Order_Original_Customer_Email+" VERCHAR,"+OrderConstant.Key_Order_Original_note+" VERCHAR,"+OrderConstant.Key_Order_Original_CustomerSignature_Id+" double," +
                OrderConstant.Key_Order_Original_CustomerSignature_url+" VERCHAR,"+OrderConstant.Key_Order_Original_CustomerSignature_Name+" VERCHAR,"+OrderConstant.Key_Order_Original_CustomerSignature_Key+" VERCHAR," +
                OrderConstant.Key_Order_Original_CustomerSignature_Size+" VERCHAR,"+OrderConstant.Key_Order_Original_CustomerSignature_MimeType+" VERCHAR,"+OrderConstant.Key_Order_Original_DriverSignature_Id+" double," +
                OrderConstant.Key_Order_Original_DriverSignature_Url+" VERCHAR,"+OrderConstant.Key_Order_Original_DriverSignature_Name+" VERCHAR,"+OrderConstant.Key_Order_Original_DriverSignature_Key+" VERCHAR," +
                OrderConstant.Key_Order_Original_DriverSignature_Size+" VERCHAR,"+OrderConstant.Key_Order_Original_DriverSignature_MimeType+" VERCHAR,"+OrderConstant.Key_Order_Destination_Date+" double,"+OrderConstant.Key_Order_Destination_Customer_Name+"  VERCHAR," +
                OrderConstant.Key_Order_Destination_Customer_AddressLines+" VERCHAR,"+OrderConstant.Key_Order_Destination_Customer_AddressCity+" VERCHAR,"+OrderConstant.Key_Order_Destination_Customer_AddressState+" VERCHAR," +
                OrderConstant.Key_Order_Destination_Customer_AddressZipCode+" VERCHAR,"+OrderConstant.Key_Order_Destination_Customer_Contact+" VERCHAR,"+OrderConstant.Key_Order_Destination_Customer_Phone+" VERCHAR," +
                OrderConstant.Key_Order_Destination_Customer_fax+" VERCHAR,"+OrderConstant.Key_Order_Destination_Customer_Email+" VERCHAR,"+OrderConstant.Key_Order_Destination_note+" VERCHAR,"+OrderConstant.Key_Order_Destination_CustomerSignature_Id+" VERCHAR," +
                OrderConstant.Key_Order_Destination_CustomerSignature_url+" VERCHAR,"+OrderConstant.Key_Order_Destination_CustomerSignature_Name+" VERCHAR,"+OrderConstant.Key_Order_Destination_CustomerSignature_Key+" VERCHAR," +
                OrderConstant.Key_Order_Destination_CustomerSignature_Size+" VERCHAR,"+OrderConstant.Key_Order_Destination_CustomerSignature_MimeType+" VERCHAR,"+OrderConstant.Key_Order_Destination_DriverSignature_Id+" double," +
                OrderConstant.Key_Order_Destination_DriverSignature_Url+" VERCHAR,"+OrderConstant.Key_Order_Destination_DriverSignature_Name+" VERCHAR,"+OrderConstant.Key_Order_Destination_DriverSignature_Key+" VERCHAR," +
                OrderConstant.Key_Order_Destination_DriverSignature_Size+" VERCHAR,"+OrderConstant.Key_Order_Destination_DriverSignature_MimeType+" VERCHAR,"+OrderConstant.Key_Order_printCopy_Id+" double," +
                OrderConstant.Key_Order_printCopy_Url+" VERCHAR,"+OrderConstant.Key_Order_Invoice_Id+" double,"+OrderConstant.Key_Order_Invoice_Url+" VERCHAR,"+OrderConstant.Key_Order_Customer_Name+" VERCHAR,"+OrderConstant.Key_Order_Customer_AddressLines+" VERCHAR," +
                OrderConstant.Key_Order_Customer_AddressCity+" VERCHAR,"+OrderConstant.Key_Order_Customer_AddressState+" VERCHAR,"+OrderConstant.Key_Order_Customer_AddressZipCode+" VERCHAR,"+OrderConstant.Key_Order_Customer_Contact+" VERCHAR," +
                OrderConstant.Key_Order_Customer_Phone+" VERCHAR,"+OrderConstant.Key_Order_Customer_fax+" VERCHAR,"+OrderConstant.Key_Order_Customer_Email+" VERCHAR,"+OrderConstant.Key_Order_Payment_Amount+" VERCHAR," +
                OrderConstant.Key_Order_Payment_PaymentMethod+" VERCHAR,"+OrderConstant.Key_Order_Payment_PaymentNote+" VERCHAR,"+OrderConstant.Key_Order_Payment_PaymentDate+" double,"+OrderConstant.Key_Order_Payment_InvoiceNumber+" VERCHAR," +
                OrderConstant.Key_Order_Payment_InvoiceNote+");";
        DROP_ORDER_TABLE = "DROP TABLE IF EXISTS `"+Order_Table_Name + "`";

        INSERT_ORDER_TABLE = "INSERT INTO `"+Order_Table_Name+"` ("+OrderConstant.Key_Order_ID+", "+OrderConstant.Key_Order_CompanyId+","
                +OrderConstant.Key_Order_number+","+OrderConstant.Key_Order_status+"," + " "+OrderConstant.Key_Order_billClient+","+OrderConstant.Key_Order_instruction+","
                +OrderConstant.Key_Order_totalPrice+","+OrderConstant.Key_Order_modifiedDate+"," + " "+OrderConstant.Key_Order_ArchivedDate+","+OrderConstant.Key_Order_BilledDate
                +","+OrderConstant.Key_Order_PaidDate+","+OrderConstant.Key_Order_Driver_id+", " + ""+OrderConstant.Key_Order_Driver_name+","
                +OrderConstant.Key_Order_Driver_Prefix+","+OrderConstant.Key_Order_Driver_FirstName+","+OrderConstant.Key_Order_Driver_LastName+"," +
                " "+OrderConstant.Key_Order_Original_Date+","+OrderConstant.Key_Order_Original_Customer_Name+","+OrderConstant.Key_Order_Original_Customer_AddressLines+"," +
                " "+OrderConstant.Key_Order_Original_Customer_AddressCity+","+OrderConstant.Key_Order_Original_Customer_AddressState+","+OrderConstant.Key_Order_Original_Customer_AddressZipCode+"," +
                " "+OrderConstant.Key_Order_Original_Customer_Contact+","+OrderConstant.Key_Order_Original_Customer_Phone+","+OrderConstant.Key_Order_Original_Customer_fax+"," +
                " "+OrderConstant.Key_Order_Original_Customer_Email+","+OrderConstant.Key_Order_Original_note+","+OrderConstant.Key_Order_Original_CustomerSignature_Id+"," +
                " "+OrderConstant.Key_Order_Original_CustomerSignature_url+","+OrderConstant.Key_Order_Original_CustomerSignature_Name+","+OrderConstant.Key_Order_Original_CustomerSignature_Key+"," +
                " "+OrderConstant.Key_Order_Original_CustomerSignature_Size+","+OrderConstant.Key_Order_Original_CustomerSignature_MimeType+","+OrderConstant.Key_Order_Original_DriverSignature_Id+"," +
                " "+OrderConstant.Key_Order_Original_DriverSignature_Url+","+OrderConstant.Key_Order_Original_DriverSignature_Name+","+OrderConstant.Key_Order_Original_DriverSignature_Key+"," +
                " "+OrderConstant.Key_Order_Original_DriverSignature_Size+","+OrderConstant.Key_Order_Original_DriverSignature_MimeType+","+OrderConstant.Key_Order_Destination_Date+","+OrderConstant.Key_Order_Destination_Customer_Name+"," +
                " "+OrderConstant.Key_Order_Destination_Customer_AddressLines+","+OrderConstant.Key_Order_Destination_Customer_AddressCity+","+OrderConstant.Key_Order_Destination_Customer_AddressState+"," +
                " "+OrderConstant.Key_Order_Destination_Customer_AddressZipCode+","+OrderConstant.Key_Order_Destination_Customer_Contact+","+OrderConstant.Key_Order_Destination_Customer_Phone+"," +
                " "+OrderConstant.Key_Order_Destination_Customer_fax+","+OrderConstant.Key_Order_Destination_Customer_Email+","+OrderConstant.Key_Order_Destination_note+","+OrderConstant.Key_Order_Destination_CustomerSignature_Id+"," +
                " "+OrderConstant.Key_Order_Destination_CustomerSignature_url+","+OrderConstant.Key_Order_Destination_CustomerSignature_Name+","+OrderConstant.Key_Order_Destination_CustomerSignature_Key+"," +
                " "+OrderConstant.Key_Order_Destination_CustomerSignature_Size+","+OrderConstant.Key_Order_Destination_CustomerSignature_MimeType+","+OrderConstant.Key_Order_Destination_DriverSignature_Id+"," +
                " "+OrderConstant.Key_Order_Destination_DriverSignature_Url+","+OrderConstant.Key_Order_Destination_DriverSignature_Name+","+OrderConstant.Key_Order_Destination_DriverSignature_Key+"," +
                " "+OrderConstant.Key_Order_Destination_DriverSignature_Size+","+OrderConstant.Key_Order_Destination_DriverSignature_MimeType+","+OrderConstant.Key_Order_printCopy_Id+"," +
                " "+OrderConstant.Key_Order_printCopy_Url+","+OrderConstant.Key_Order_Invoice_Id+","+OrderConstant.Key_Order_Invoice_Url+","+OrderConstant.Key_Order_Customer_Name+","+OrderConstant.Key_Order_Customer_AddressLines+"," +
                " "+OrderConstant.Key_Order_Customer_AddressCity+","+OrderConstant.Key_Order_Customer_AddressState+","+OrderConstant.Key_Order_Customer_AddressZipCode+","+OrderConstant.Key_Order_Customer_Contact+"," +
                " "+OrderConstant.Key_Order_Customer_Phone+","+OrderConstant.Key_Order_Customer_fax+","+OrderConstant.Key_Order_Customer_Email+","+OrderConstant.Key_Order_Payment_Amount+"," +
                " "+OrderConstant.Key_Order_Payment_PaymentMethod+","+OrderConstant.Key_Order_Payment_PaymentNote+","+OrderConstant.Key_Order_Payment_PaymentDate+","+OrderConstant.Key_Order_Payment_InvoiceNumber+"," +
                " "+OrderConstant.Key_Order_Payment_InvoiceNote+") VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    public void CreateOrderTable(SQLiteDatabase sqLiteDatabase){
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(CREATE_ORDER_TABLE);
        stmt.execute();
    }
    public void DropOrderTable(SQLiteDatabase sqLiteDatabase){
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(DROP_ORDER_TABLE);
        stmt.execute();
    }
    public void InsertData(List<Map<String, Object>> list, SQLiteDatabase sqLiteDatabase){
        for(Map<String, Object> o : list){
            SQLiteStatement stmt = sqLiteDatabase.compileStatement(INSERT_ORDER_TABLE);
            if(o.containsKey("id")){
                stmt.bindDouble(1, (Double)o.get("id"));
            }
            if(o.containsKey("companyId")){
                stmt.bindDouble(2, (Double)o.get("companyId"));
            }
            if(o.containsKey("number")){
                stmt.bindString(3, (String) o.get("number"));
            }
            if(o.containsKey("status")){
                stmt.bindString(4, (String)o.get("status"));
            }
            if(o.containsKey("billClient")){
                stmt.bindString(5, (String)o.get("billClient"));
            }
            if(o.containsKey("original")){
                List<Integer> list2 = new ArrayList<>();
                list2.add(17);
                list2.add(27);
                for(int i = 18; i< 27; i++){
                    list2.add(i);
                }
                for (int j = 28; j<40; j++){
                    list2.add(j);
                }
                Map<String, Object> original = (Map<String, Object>)o.get("original");
                addMapData(stmt, original, list2);
            }
            if(o.containsKey("destination")){
                List<Integer> list3 = new ArrayList<>();
                list3.add(40);
                list3.add(50);
                for(int i = 41; i<50; i++){
                    list3.add(i);
                }
                for(int j = 51; j<63; j++){
                    list3.add(j);
                }
                Map<String, Object> destination = (Map<String, Object>)o.get("destination");
                addMapData(stmt, destination, list3);
            }
            if(o.containsKey("archivedDate")){
                stmt.bindString(9, (String)o.get("archivedDate"));
            }
            if(o.containsKey("printCopy")){
                Map<String, Object> printCopy = (Map<String, Object>)o.get("printCopy");
                if(printCopy.containsKey("id")){
                    stmt.bindString(63, (String)o.get("id"));
                }
                if(printCopy.containsKey("url")){
                    stmt.bindString(64, (String)o.get("url"));
                }
            }
            if(o.containsKey("instruction")){
                stmt.bindString(6, (String)o.get("instruction"));
            }
            if(o.containsKey("customer")){
                addCustomerData(stmt, o);
            }
            if(o.containsKey("billedDate")){
                stmt.bindDouble(10, (double)o.get("billedDate"));
            }
            if(o.containsKey("paidDate")){
                stmt.bindDouble(11, (double)o.get("paidDate"));
            }
            if(o.containsKey("totalPrice")){
                stmt.bindDouble(7, (double)o.get("totalPrice"));
            }
            if(o.containsKey("payment")){
                Map<String, Object> payment = (Map<String, Object>)o.get("payment");
                if(payment.containsKey("amount")){
                    stmt.bindDouble(76, (double)payment.get("amount"));
                }
                if(payment.containsKey("paymentMethod")){
                    stmt.bindString(77, (String)payment.get("paymentMethod"));
                }
                if(payment.containsKey("paymentNote")){
                    stmt.bindString(78, (String)payment.get("paymentNote"));
                }
                if(payment.containsKey("paymentDate")){
                    stmt.bindString(79, (String)payment.get("paymentDate"));
                }
                if(payment.containsKey("invoiceNumber")){
                    stmt.bindString(80, (String)payment.get("invoiceNumber"));
                }
                if(payment.containsKey("invoiceNote")){
                    stmt.bindString(81, (String)payment.get("invoiceNote"));
                }
            }
            if(o.containsKey("modifiedDate")){
                stmt.bindDouble(8, (double)o.get("modifiedDate"));
            }
            if(o.containsKey("invoice")){
                Map<String, Object> invoice = (Map<String, Object>)o.get("invoice");
                if(invoice.containsKey("id")){
                    stmt.bindDouble(65, (double)invoice.get("id"));
                }
                if(invoice.containsKey("url")){
                    stmt.bindString(66, (String)invoice.get("url"));
                }
            }
            stmt.execute();
        }

    }
    public void InsertData(Map<String, Object> map, SQLiteDatabase sqLiteDatabase){
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(INSERT_ORDER_TABLE);
        if(map.containsKey("id")){
            stmt.bindDouble(1, (Double)map.get("id"));
        }
        if(map.containsKey("companyId")){
            stmt.bindDouble(2, (Double)map.get("companyId"));
        }
        if(map.containsKey("number")){
            stmt.bindString(3, (String) map.get("number"));
        }
        if(map.containsKey("status")){
            stmt.bindString(4, (String)map.get("status"));
        }
        if(map.containsKey("billClient")){
            stmt.bindString(5, (String)map.get("billClient"));
        }
        if(map.containsKey("original")){
            List<Integer> list2 = new ArrayList<>();
            list2.add(17);
            list2.add(27);
            for(int i = 18; i< 27; i++){
                list2.add(i);
            }
            for (int j = 28; j<40; j++){
                list2.add(j);
            }
            Map<String, Object> original = (Map<String, Object>)map.get("original");
            addMapData(stmt, original, list2);
        }
        if(map.containsKey("destination")){
            List<Integer> list3 = new ArrayList<>();
            list3.add(40);
            list3.add(50);
            for(int i = 41; i<50; i++){
                list3.add(i);
            }
            for(int j = 51; j<63; j++){
                list3.add(j);
            }
            Map<String, Object> destination = (Map<String, Object>)map.get("destination");
            addMapData(stmt, destination, list3);
        }
        if(map.containsKey("archivedDate")){
            stmt.bindString(9, (String)map.get("archivedDate"));
        }
        if(map.containsKey("printCopy")){
            Map<String, Object> printCopy = (Map<String, Object>)map.get("printCopy");
            if(printCopy.containsKey("id")){
                stmt.bindString(63, (String)map.get("id"));
            }
            if(printCopy.containsKey("url")){
                stmt.bindString(64, (String)map.get("url"));
            }
        }
        if(map.containsKey("instruction")){
            stmt.bindString(6, (String)map.get("instruction"));
        }
        if(map.containsKey("customer")){
            addCustomerData(stmt, map);
        }
        if(map.containsKey("billedDate")){
            stmt.bindString(10, (String)map.get("billedDate"));
        }
        if(map.containsKey("paidDate")){
            stmt.bindString(11, (String)map.get("paidDate"));
        }
        if(map.containsKey("totalPrice")){
            stmt.bindDouble(7, (double)map.get("totalPrice"));
        }
        if(map.containsKey("payment")){
            Map<String, Object> payment = (Map<String, Object>)map.get("payment");
            if(payment.containsKey("amount")){
                stmt.bindDouble(76, (double)payment.get("amount"));
            }
            if(payment.containsKey("paymentMethod")){
                stmt.bindString(77, (String)payment.get("paymentMethod"));
            }
            if(payment.containsKey("paymentNote")){
                stmt.bindString(78, (String)payment.get("paymentNote"));
            }
            if(payment.containsKey("paymentDate")){
                stmt.bindString(79, (String)payment.get("paymentDate"));
            }
            if(payment.containsKey("invoiceNumber")){
                stmt.bindString(80, (String)payment.get("invoiceNumber"));
            }
            if(payment.containsKey("invoiceNote")){
                stmt.bindString(81, (String)payment.get("invoiceNote"));
            }
        }
        if(map.containsKey("modifiedDate")){
            stmt.bindDouble(8, (double)map.get("modifiedDate"));
        }
        if(map.containsKey("invoice")){
            Map<String, Object> invoice = (Map<String, Object>)map.get("invoice");
            if(invoice.containsKey("id")){
                stmt.bindString(65, (String)invoice.get("id"));
            }
            if(invoice.containsKey("url")){
                stmt.bindString(66, (String)invoice.get("url"));
            }
        }
        stmt.execute();
    }
    private void addMapData(SQLiteStatement stmt, Map<String, Object> map, List<Integer> list) {
        if(map.containsKey("date")){
            stmt.bindString(list.get(0), (String)map.get("date"));
        }
        if(map.containsKey("note")){
            stmt.bindString(list.get(1), (String)map.get("note"));
        }
        if(map.containsKey("customer")){
            Map<String, Object> customer = (Map<String, Object>)map.get("customer");
            if(customer.containsKey("name")){
                stmt.bindString(list.get(2), (String)customer.get("name"));
            }
            if(customer.containsKey("addressLines")){
                stmt.bindString(list.get(3), (String)customer.get("addressLines"));
            }
            if(customer.containsKey("addressCity")){
                stmt.bindString(list.get(4), (String)customer.get("addressCity"));
            }
            if(customer.containsKey("addressState")){
                stmt.bindString(list.get(5), (String)customer.get("addressState"));
            }
            if(customer.containsKey("addressZipcode")){
                stmt.bindString(list.get(6), (String)customer.get("addressZipcode"));
            }
            if(customer.containsKey("contact")){
                stmt.bindString(list.get(7), (String)customer.get("contact"));
            }
            if(customer.containsKey("phone")){
                stmt.bindString(list.get(8), (String)customer.get("phone"));
            }
            if(customer.containsKey("fax")){
                stmt.bindString(list.get(9), (String)customer.get("fax"));
            }
            if(customer.containsKey("email")){
                stmt.bindString(list.get(10), (String)customer.get("email"));
            }
        }
        if(map.containsKey("customerSignature")){
            Map<String, Object> customerSignature = (Map<String, Object>)map.get("customerSignature");
            List<Integer> list2 = list.subList(11, 17);
            addMap2Data(stmt, customerSignature, list2);
        }
        if(map.containsKey("driverSignature")){
            Map<String, Object> driverSignature = (Map<String, Object>)map.get("driverSignature");
            List<Integer> list2 = list.subList(17, 23);
            addMap2Data(stmt, driverSignature, list2);
        }
    }

    private void addMap2Data(SQLiteStatement stmt, Map<String, Object> map, List<Integer> list) {
        Map<String, Object> customerSignature = (Map<String, Object>)map.get("customerSignature");
        if(customerSignature.containsKey("id")){
            stmt.bindDouble(list.get(0), (double)customerSignature.get("id"));
        }
        if(customerSignature.containsKey("url")){
            stmt.bindString(list.get(1), (String)customerSignature.get("url"));
        }
        if(customerSignature.containsKey("name")){
            stmt.bindString(list.get(2), (String)customerSignature.get("name"));
        }
        if(customerSignature.containsKey("key")){
            stmt.bindString(list.get(3), (String)customerSignature.get("key"));
        }
        if(customerSignature.containsKey("size")){
            stmt.bindString(list.get(4), (String)customerSignature.get("size"));
        }
        if(customerSignature.containsKey("mimeType")){
            stmt.bindString(list.get(5), (String)customerSignature.get("mimeType"));
        }
    }

    private void addCustomerData(SQLiteStatement stmt, Map<String, Object> o) {
        Map<String, Object> customer = (Map<String, Object>)o.get("customer");
        if(customer.containsKey("name")){
            stmt.bindString(67, (String)customer.get("name"));
        }
        if(customer.containsKey("addressLines")){
            stmt.bindString(68, (String)customer.get("addressLines"));
        }
        if(customer.containsKey("addressCity")){
            stmt.bindString(69, (String)customer.get("addressCity"));
        }
        if(customer.containsKey("addressState")){
            stmt.bindString(70, (String)customer.get("addressState"));
        }
        if(customer.containsKey("addressZipcode")){
            stmt.bindString(71, (String)customer.get("addressZipcode"));
        }
        if(customer.containsKey("contact")){
            stmt.bindString(72, (String)customer.get("contact"));
        }
        if(customer.containsKey("phone")){
            stmt.bindString(73, (String)customer.get("phone"));
        }
        if(customer.containsKey("fax")){
            stmt.bindString(74, (String)customer.get("fax"));
        }
        if(customer.containsKey("email")){
            stmt.bindString(75, (String)customer.get("email"));
        }
    }

    public Cursor SelectData(String target, SQLiteDatabase sqLiteDatabase){
        String qry = "SELECT " + target + " FROM `"+Order_Table_Name+"`;";
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectData2(String target, SQLiteDatabase sqLiteDatabase){
        String qry = "SELECT " + target + " FROM `"+Order_Table_Name+"`;";
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        return cursor;
    }
}
