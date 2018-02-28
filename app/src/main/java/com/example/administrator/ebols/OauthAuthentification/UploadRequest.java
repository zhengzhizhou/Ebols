package com.example.administrator.ebols.OauthAuthentification;

import com.example.administrator.ebols.Otto.OrderToJson;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */

public class UploadRequest {
    private String orderId;
    private String tab;
    private long id;
    private long companyId;
    private Map<String, Object> driver;
    private int number;
    private String status;
    private String billClient;
    private Map<String, Object> original;
    private Map<String, Object> destination;
    private Map<String, Object> printCopy;
    private Map<String, Object> invoice;
    private String instruction;
    private List<Map<String, Object>> vehicles;
    private Map<String, Object> customer;
    private String billedDate;
    private String paidDate;
    private String totalPrice;
    private Map<String, Object> payment;
    private String modifiedDate;
    private String state;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getPayment() {
        return payment;
    }

    public void setPayment(Map<String, Object> payment) {
        this.payment = payment;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Map<String, Object> getDriver() {
        return driver;
    }

    public void setDriver(Map<String, Object> driver) {
        this.driver = driver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillClient() {
        return billClient;
    }

    public void setBillClient(String billClient) {
        this.billClient = billClient;
    }

    public Map<String, Object> getOriginal() {
        return original;
    }

    public void setOriginal(Map<String, Object> original) {
        this.original = original;
    }

    public Map<String, Object> getDestination() {
        return destination;
    }

    public void setDestination(Map<String, Object> destination) {
        this.destination = destination;
    }

    public Map<String, Object> getPrintCopy() {
        return printCopy;
    }

    public void setPrintCopy(Map<String, Object> printCopy) {
        this.printCopy = printCopy;
    }

    public Map<String, Object> getInvoice() {
        return invoice;
    }

    public void setInvoice(Map<String, Object> invoice) {
        this.invoice = invoice;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Map<String, Object>> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Map<String, Object>> vehicles) {
        this.vehicles = vehicles;
    }

    public Map<String, Object> getCustomer() {
        return customer;
    }

    public void setCustomer(Map<String, Object> customer) {
        this.customer = customer;
    }

    public String getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(String billedDate) {
        this.billedDate = billedDate;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public static class Payment{
        public String paymentMethod, paymentNote, paymentDate, invoiceNumber, invoiceNote;
        public double amount;

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getPaymentNote() {
            return paymentNote;
        }

        public void setPaymentNote(String paymentNote) {
            this.paymentNote = paymentNote;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public String getInvoiceNote() {
            return invoiceNote;
        }

        public void setInvoiceNote(String invoiceNote) {
            this.invoiceNote = invoiceNote;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
