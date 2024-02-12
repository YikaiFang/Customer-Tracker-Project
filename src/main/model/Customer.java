package model;

/*
 This class is the customer class and creates a customer object with a name, vinNumber,
 phone number and an email
 */

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Car> cars;
    private String phoneNumber;
    private String email;

    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void remove(Car car) {
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
}
