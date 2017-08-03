package com.example.administrator.ebols.DB.TableData;

/**
 * Created by Administrator on 2017/7/21.
 */

public class OrderTable {
    private static final String Order_Table_Name = "order";

    private static final String Key_Order_ID = "id";
    private static final String Key_Order_CompanyId = "companyId";
    private static final String Key_Order_number = "number";
    private static final String Key_Order_status ="status";
    private static final String Key_Order_billClient="billClient";
    private static final String Key_Order_instruction="instruction";
    private static final String Key_Order_totalPrice = "totalPrice";
    private static final String Key_Order_modifiedDate = "modifiedDate";
    private static final String Key_Order_ArchivedDate = "archivedDate";
    private static final String Key_Order_BilledDate = "billedDate";
    private static final String Key_Order_PaidDate = "paidDate";

    private static final String CREATE_ORDER_TABLE="CREATE TABLE IF NOT EXISTS "
            +Order_Table_Name+" ("
            +Key_Order_ID+" VERCHAR, "
            +Key_Order_CompanyId+" VERCHAR, "
            +Key_Order_number+" VERCHAR, "
            +Key_Order_status+" VERCHAR, "
            +Key_Order_billClient+" VERCHAR NOT NULL, "
            +Key_Order_ArchivedDate+" VERCHAR, "
            +Key_Order_instruction+" VERCHAR, "
            +Key_Order_billClient + " VERCHAR, "
            +Key_Order_PaidDate + " VERCHAR, "
            +Key_Order_totalPrice+" VERCHAR, "
            +Key_Order_modifiedDate+" VERCHAR);";
}
