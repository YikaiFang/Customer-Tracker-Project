package model;

/*
 This class is the customer class and creates a customer object with a name, vinNumber,
 phone number and an email
 */

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;

public class Customer implements Writable {

    private String name;
    private ArrayList<Car> cars;
    private String phoneNumber;
    private String email;

    //REQUIRES: phone number must be a combination of 10 numbers 0-9 and email must end with @________
    //EFFECTS: creates a customer with a name phone number and email and an empty list of cars.
    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        cars = new ArrayList<>();
    }

    //EFFECTS: finds the customer in a list of customers with the name and returns that customer
    public Customer findCustomer(ArrayList<Customer> customers, String name) {
        for (Customer c : customers) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    //MODIFIES: this
    //EFFECTS: add a car to the current list of cars and prints the list of cars
    public void addCar(Car car) {
        cars.add(car);
    }

    //REQUIRES: name cannot be an empty string
    //MODIFIES: this
    //EFFECTS : changes the name of a customer
    public void changeName(String name) {
        this.name = name;
    }

    //REQUIRES: email cannot be an empty string
    //MODIFIES: this
    //EFFECTS : changes the email of a customer
    public void changeEmail(String email) {
        this.email = email;
    }

    //REQUIRES: phone number must not be empty and should be a combination of 10 numbers 0-9
    //MODIFIES: this
    //EFFECTS : changes the email of a customer
    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //MODIFIES: this
    //EFFECTS : removes the given car from the list of cars and prints a message
    public void removeCar(Car car) {
        cars.remove(car);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phone number", phoneNumber);
        json.put("email", email);
        json.put("cars", carToJson());
        return json;
    }

    private JSONArray carToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Car c : cars) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
