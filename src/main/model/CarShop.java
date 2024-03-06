package model;

import java.util.ArrayList;

// a CarShop class to hold the

public class CarShop {

    private String name;
    private ArrayList<Customer> customers;

    public CarShop(String name) {
        customers = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}
