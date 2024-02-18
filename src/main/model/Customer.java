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

    //REQUIRES: phone number must be a combination of 10 numbers 0-9 and email must end with @________
    //EFFECTS: creates a customer with a name phone number and email and an empty list of cars.
    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        cars = new ArrayList<>();
    }

    public void something() {
        //TODO figure something out for this
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
    public String remove(Car car) {
        cars.remove(car);
        return car.getMake() + car.getModel();
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
