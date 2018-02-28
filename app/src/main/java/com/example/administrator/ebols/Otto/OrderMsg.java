package com.example.administrator.ebols.Otto;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/9/5.
 */

public class OrderMsg {
    private Map<String, Object> destination, original, order, driver, customer, customerOfOriginal, customerOfDestination,
            customerSignatureOfOriginal, customerSignatureOfDestination, driverSignatureOfOriginal, driverSignatureOfDestination,
            payment, invoice;
    private List<Map<String, Object>> vehicles;
    private Map<String, Object> vehicle;
    public OrderMsg(){
        destination = new HashMap<String, Object>();
        original = new HashMap<String, Object>();
        order = new HashMap<String, Object>();
        driver = new HashMap<String, Object>();
        customer = new HashMap<String, Object>();
        vehicle = new HashMap<String, Object>();
        vehicles = new ArrayList<>();
        customerOfOriginal = new HashMap<String, Object>();
        customerOfDestination = new HashMap<String, Object>();
        customerSignatureOfOriginal = new HashMap<String, Object>();
        customerSignatureOfDestination = new HashMap<String, Object>();
        driverSignatureOfOriginal = new HashMap<String, Object>();
        driverSignatureOfDestination = new HashMap<String, Object>();
        payment = new HashMap<>();
    }

    public void addOrder(String key, String value){
        order.put(key, value);
    }
    public void addPayment(String key, String value){
        payment.put(key, value);
    }
    public void addInvoice(String key, String value){
        invoice.put(key, value);
    }
    public void addOrder(String key){
        if(key=="vehicles"){
            order.put("vehicles", vehicles);
        }else if(key == "original"){
            order.put(key, original);
        }else if(key == "destination"){
            order.put(key, destination);
        }else if(key == "driver"){
            order.put(key, driver);
        }else if(key == "customer"){
            order.put(key, customer);
        }else if(key == "payment"){
            order.put(key, payment);
        }else if(key == "invoice"){
            order.put(key, invoice);
        }else{
            Log.d("Order:", "error");
        }
    }

    public void addVehicle(String key, String value){
        vehicle.put(key, value);
    }

    public void addVehicles(List<Map<String, String>> map, int size){
        List<String> key;
        List<String> value;
        for(int i = 0; i< size; i++){
            vehicle.put("year", map.get(i).get("year"));
            vehicle.put("make", map.get(i).get("make"));
            vehicle.put("model", map.get(i).get("model"));
            vehicle.put("color", map.get(i).get("color"));
            vehicle.put("vin", map.get(i).get("vin"));
            vehicle.put("price", map.get(i).get("price"));
            vehicles.add(vehicle);
        }
    }
    public void addOriginal(String key, Object object){
        original.put(key, object);
    }

    public void addOriginal(String key){
        if(key == "customer"){
            original.put(key, customerOfOriginal);
        }else if (key == "customerSignature"){
            original.put(key, customerSignatureOfOriginal);
        }else if (key == "driverSignature"){
            original.put(key, driverSignatureOfDestination);
        }else{
            Log.d("Original: ", "error");
        }
    }
    public void addDestination(String key, String value){
        destination.put(key, value);
    }

    public void addDestination(String key){
        if(key == "customer"){
            destination.put(key, customerOfDestination);
        }else if (key == "customerSignauture"){
            destination.put(key, customerSignatureOfDestination);
        }else if (key == "DriverSignature"){
            destination.put(key, driverSignatureOfDestination);
        }else{
            Log.d("destination: ", "Error");
        }
    }

    public void addCustomerOfDestination(String key, String value){
        customerOfDestination.put(key, value);
    }

    public void addCustomerSignatureOfDestination(String key, String value){
        customerSignatureOfDestination.put(key, value);
    }

    public void addDriverSignatureOfDestination(String key, String value){
        driverSignatureOfDestination.put(key, value);
    }
    public void addCustomerOfOriginal(String key, String value){
        customerOfOriginal.put(key, value);
    }

    public void addCustomerSignatureOfOriginal(String key, String value){
        customerSignatureOfOriginal.put(key, value);
    }

    public void addDriverSignatureOfOriginal(String key, String value){
        driverSignatureOfOriginal.put(key, value);
    }


    public void addDriver(String key, String value){
        driver.put(key, value);
    }

    public void addCustomer(String key, String value){
        customer.put(key, value);
    }

    public Map<String, Object> getDestination() {
        return destination;
    }

    public Map<String, Object> getOriginal() {
        return original;
    }

    public Map<String, Object> getOrder() {
        return order;
    }

    public Map<String, Object> getDriver() {
        return driver;
    }

    public Map<String, Object> getCustomer() {
        return customer;
    }

    public List<Map<String,Object>> getVehicle() {
        return vehicles;
    }

    public Map<String, Object> getCustomerOfOriginal() {
        return customerOfOriginal;
    }

    public Map<String, Object> getCustomerOfDestination() {
        return customerOfDestination;
    }

    public Map<String, Object> getCustomerSignatureOfOriginal() {
        return customerSignatureOfOriginal;
    }

    public Map<String, Object> getCustomerSignatureOfDestination() {
        return customerSignatureOfDestination;
    }

    public Map<String, Object> getDriverSignatureOfOriginal() {
        return driverSignatureOfOriginal;
    }

    public Map<String, Object> getDriverSignatureOfDestination() {
        return driverSignatureOfDestination;
    }

}
