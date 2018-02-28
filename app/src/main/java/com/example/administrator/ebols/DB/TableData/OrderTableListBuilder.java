package com.example.administrator.ebols.DB.TableData;

import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.Object.original;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class OrderTableListBuilder {
    public List<Object> list;
    public OrderTableListBuilder(List<Object>  list){
        this.list = list;
    }
    public void Build(int location, retrofit2.Response<OrdersResponse> response){
        LinkedTreeMap<String, Object> original = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("original");
        LinkedTreeMap<String, Object> driver = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("driver");
        LinkedTreeMap<String, Object> destination = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("destination");
        LinkedTreeMap<String, Object> printCopy = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("printCopy");
        LinkedTreeMap<String, Object> invoice = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("invoice");
        LinkedTreeMap<String, Object> customer = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("customer");
        LinkedTreeMap<String, Object> payment = (LinkedTreeMap<String, Object>) response.body().getList().get(location).get("payment");
        List<LinkedTreeMap<String, Object>> vehicles = (List<LinkedTreeMap<String,Object>>)response.body().getList().get(location).get("vehicles");

        LinkedTreeMap<String, LinkedTreeMap<String, Object>> originalCustomer = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("original")).get("customer");
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> originalCustomerSignature = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("original")).get("customerSignature");
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> originalDriverSignature = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("original")).get("driverSignature");
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> destinationCustomer = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("destination")).get("customer");
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> destinationCustomerSignature = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("destination")).get("customerSignature");
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> destinationDriverSignature = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) response.
                body().getList().get(location).get("destination")).get("driverSignature");

        list.add((Object) response.body().getList().get(location).get("id"));
        list.add((Object) response.body().getList().get(location).get("companyId"));
        list.add((Object) response.body().getList().get(location).get("number"));
        list.add((Object) response.body().getList().get(location).get("status"));
        list.add((Object) response.body().getList().get(location).get("billClient"));
        list.add((Object) response.body().getList().get(location).get("instruction"));
        list.add((Object) response.body().getList().get(location).get("totalPrice"));
        list.add((Object) response.body().getList().get(location).get("modifiedDate"));
        list.add((Object) response.body().getList().get(location).get("archivedDate"));
        list.add((Object) response.body().getList().get(location).get("billedDate"));
        list.add((Object) response.body().getList().get(location).get("paidDate"));
        if(driver!=null){
            list.add(driver.get("id"));
            list.add(driver.get("name"));
            list.add(driver.get("prefix"));
            list.add(driver.get("firstName"));
            list.add(driver.get("lastName"));
        }
        if(original!=null){
            list.add(original.get("date"));
            if(originalCustomer!=null){
                list.add(originalCustomer.get("name"));
                list.add(originalCustomer.get("addressLines"));
                list.add(originalCustomer.get("addressCity"));
                list.add(originalCustomer.get("addressState"));
                list.add(originalCustomer.get("addressZipcode"));
                list.add(originalCustomer.get("contact"));
                list.add(originalCustomer.get("phone"));
                list.add(originalCustomer.get("fax"));
                list.add(originalCustomer.get("email"));
            }
            list.add(original.get("note"));
            if(originalCustomerSignature!=null){
                list.add(originalCustomerSignature.get("id"));
                list.add(originalCustomerSignature.get("url"));
                list.add(originalCustomerSignature.get("name"));
                list.add(originalCustomerSignature.get("key"));
                list.add(originalCustomerSignature.get("size"));
                list.add(originalCustomerSignature.get("mimeType"));
            }
            if(originalDriverSignature!= null){
                list.add(originalDriverSignature.get("id"));
                list.add(originalDriverSignature.get("url"));
                list.add(originalDriverSignature.get("name"));
                list.add(originalDriverSignature.get("key"));
                list.add(originalDriverSignature.get("size"));
                list.add(originalDriverSignature.get("mimeType"));
            }
        }
        if(destination!=null){
            list.add(destination.get("date"));
            if(destinationCustomer!=null){
                list.add(destinationCustomer.get("name"));
                list.add(destinationCustomer.get("addressLines"));
                list.add(destinationCustomer.get("addressCity"));
                list.add(destinationCustomer.get("addressState"));
                list.add(destinationCustomer.get("addressZipcode"));
                list.add(destinationCustomer.get("contact"));
                list.add(destinationCustomer.get("phone"));
                list.add(destinationCustomer.get("fax"));
                list.add(destinationCustomer.get("email"));
            }
            list.add(destination.get("note"));
            if(destinationCustomerSignature!=null){
                list.add(destinationCustomerSignature.get("id"));
                list.add(destinationCustomerSignature.get("url"));
                list.add(destinationCustomerSignature.get("name"));
                list.add(destinationCustomerSignature.get("key"));
                list.add(destinationCustomerSignature.get("size"));
                list.add(destinationCustomerSignature.get("mimeType"));
            }
            if(destinationDriverSignature!= null){
                list.add(originalDriverSignature.get("id"));
                list.add(originalDriverSignature.get("url"));
                list.add(originalDriverSignature.get("name"));
                list.add(originalDriverSignature.get("key"));
                list.add(originalDriverSignature.get("size"));
                list.add(originalDriverSignature.get("mimeType"));
            }
        }
        if(printCopy!= null){
            list.add(printCopy.get("id"));
            list.add(printCopy.get("url"));
        }
        if(invoice!= null){
            list.add(invoice.get("id"));
            list.add(invoice.get("url"));
        }
        if(customer!=null){
            list.add(customer.get("name"));
            list.add(customer.get("addressLines"));
            list.add(customer.get("addressCity"));
            list.add(customer.get("addressState"));
            list.add(customer.get("addressZipcode"));
            list.add(customer.get("contact"));
            list.add(customer.get("phone"));
            list.add(customer.get("fax"));
            list.add(customer.get("email"));
        }
        if(payment!=null){
            list.add(payment.get("amount"));
            list.add(payment.get("paymentMethod"));
            list.add(payment.get("paymentNote"));
            list.add(payment.get("paymentDate"));
            list.add(payment.get("invoiceNumber"));
            list.add(payment.get("invoiceNote"));
        }
    }
    public List<Object> getList(){
        return list;
    }
}
