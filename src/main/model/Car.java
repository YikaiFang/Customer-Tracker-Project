package model;

/*
A car class with a vin number, year, make, and model
 */

import java.util.ArrayList;

public class Car {

    private String vinNumber;
    private int year;
    private String make;
    private String model;

    // REQUIRES: vinNumber must be a combination of exactly  17 capital letters and numbers
    // EFFECTS: creates a car with a vin number and using the vin number
    //          to determine the make, model, and year of the car
    public Car(String vinNumber) {
        this.vinNumber = vinNumber;
        String[] splitUpVinNumber = vinNumber.split("");

    }

    public String getVinNumber() {
        return vinNumber;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
