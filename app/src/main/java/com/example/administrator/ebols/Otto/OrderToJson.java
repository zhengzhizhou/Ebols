package com.example.administrator.ebols.Otto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/26.
 */

public class OrderToJson {
    long id,companyId;
    Map<String, String> driver, printCopy, invoice;
    String modifiedDate, billedDate, paidDate, totalPrice, status, billClient, archivedDate, instruction;
    Place original,destination;
    List<Vehicle> vehicles;
    Payment payment;
    Customer customer;
    int number;
    public String getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(String archivedDate) {
        this.archivedDate = archivedDate;
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

    public Map<String, String> getDriver() {
        return driver;
    }

    public void setDriver(Map<String, String> driver) {
        this.driver = driver;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Place getOriginal() {
        return original;
    }

    public void setOriginal(Place original) {
        this.original = original;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public Map<String, String> getPrintCopy() {
        return printCopy;
    }

    public void setPrintCopy(Map<String, String> printCopy) {
        this.printCopy = printCopy;
    }

    public Map<String, String> getInvoice() {
        return invoice;
    }

    public void setInvoice(Map<String, String> invoice) {
        this.invoice = invoice;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public static class Payment{
        double amount;
        String paymentMethod;
        String paymentNote;
        String paymentDate;
        String invoiceNumber;
        String invoiceNote;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

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
    }
    public static class Place{
        String date;
        Customer customer;
        String note;
        Map<String, String> customerSignature = new HashMap<>();
        Map<String, String> driverSignature = new HashMap<>();

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Map<String, String> getCustomerSignature() {
            return customerSignature;
        }

        public void setCustomerSignature(Map<String, String> customerSignature) {
            this.customerSignature = customerSignature;
        }

        public Map<String, String> getDriverSignature() {
            return driverSignature;
        }

        public void setDriverSignature(Map<String, String> driverSignature) {
            this.driverSignature = driverSignature;
        }

        public static class Customer {
            String name;
            String addressLines;
            String addressCity;
            String addressState;
            String addressZipcode;
            String contact;
            String phone;
            String fax;
            String email;

            public String getAddressCity() {
                return addressCity;
            }

            public void setAddressCity(String addressCity) {
                this.addressCity = addressCity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddressLines() {
                return addressLines;
            }

            public void setAddressLines(String addressLines) {
                this.addressLines = addressLines;
            }

            public String getAddressState() {
                return addressState;
            }

            public void setAddressState(String addressState) {
                this.addressState = addressState;
            }

            public String getAddressZipcode() {
                return addressZipcode;
            }

            public void setAddressZipcode(String addressZipcode) {
                this.addressZipcode = addressZipcode;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getFax() {
                return fax;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
    public static class Vehicle{
        long id;
        String lotNumber;
        int makeYear;
        String make;
        String model;
        String type;
        String colour;
        String vin;
        double price;
        String odometerValue;
        String inspectionNote;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLotNumber() {
            return lotNumber;
        }

        public void setLotNumber(String lotNumber) {
            this.lotNumber = lotNumber;
        }

        public int getMakeYear() {
            return makeYear;
        }

        public void setMakeYear(int makeYear) {
            this.makeYear = makeYear;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getOdometerValue() {
            return odometerValue;
        }

        public void setOdometerValue(String odometerValue) {
            this.odometerValue = odometerValue;
        }

        public String getInspectionNote() {
            return inspectionNote;
        }

        public void setInspectionNote(String inspectionNote) {
            this.inspectionNote = inspectionNote;
        }
    }
    public static class Customer{
        long id;
        String name;
        String addressLines;
        String addressCity;
        String addressState;
        String addressZipcode;
        String contact;
        String phone;
        String fax;
        String email;
        int companyId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddressLines() {
            return addressLines;
        }

        public void setAddressLines(String addressLines) {
            this.addressLines = addressLines;
        }

        public String getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(String addressCity) {
            this.addressCity = addressCity;
        }

        public String getAddressState() {
            return addressState;
        }

        public void setAddressState(String addressState) {
            this.addressState = addressState;
        }

        public String getAddressZipcode() {
            return addressZipcode;
        }

        public void setAddressZipcode(String addressZipcode) {
            this.addressZipcode = addressZipcode;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }
    }
}
