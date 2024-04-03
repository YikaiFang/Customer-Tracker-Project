package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

// a CarShop class to hold the
public class CarShop implements Writable {

    private final String name;
    private final ArrayList<Customer> customers;

    //EFFECTS: constructor
    public CarShop(String name) {
        customers = new ArrayList<>();
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS : adds customer to the list of customer
    public void addCustomer(Customer customer) {
        EventLog.getInstance().logEvent(new Event("Added new customer: " + customer.getName()));
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        EventLog.getInstance().logEvent(new Event("Customer removed: " + customer.getName()));
        customers.add(customer);
    }

    public ArrayList<Event> printEventLog(Iterator<Event> events) {
        ArrayList<Event> allEvents = new ArrayList<>();
        for (Iterator<Event> it = events; it.hasNext(); ) {
            Event e = it.next();
            allEvents.add(e);
        }
        return allEvents;
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
