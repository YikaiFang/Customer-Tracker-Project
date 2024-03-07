package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;

// a CarShop class to hold the
public class CarShop implements Writable {

    private String name;
    private ArrayList<Customer> customers;

    //EFFECTS: constructor
    public CarShop(String name) {
        customers = new ArrayList<>();
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS : adds customer to the list of customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("customer", customersToJson());
        return json;
    }

    // EFFECTS: returns customers in this carShop as a JSON array
    private JSONArray customersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Customer c : customers) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
