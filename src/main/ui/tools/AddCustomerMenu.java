package ui.tools;

import model.Car;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

// is a customer adder menu where user inputs all the needed information excluding cars of a customer
public class AddCustomerMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    public AddCustomerMenu(ArrayList<Customer> customers) {
        this.customers = customers;
        runAddCustomerMenu();
    }

    private void runAddCustomerMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        addNewCustomer();
    }

    private void addNewCustomer() {
        System.out.println("Please enter name: ");
        String name = input.next();
        System.out.println("Please enter email: ");
        String email = input.next();
        System.out.println("Please enter phone number: ");
        String phoneNumber = input.next();
        Customer addedCustomer = new Customer(name, email, phoneNumber);
        System.out.println("Please enter vin Number of car: ");
        String vin = input.next().toUpperCase();
        System.out.println("Please enter make of the car: ");
        String make = input.next();
        System.out.println("Please enter the model of the car: ");
        String model = input.next();
        Car addedCar = new Car(vin, make, model);
        addedCustomer.addCar(addedCar);
        System.out.println("Added new car " + addedCar.getYear() +  " " + make +  " " + model
                + " to " + addedCustomer.getName());
        customers.add(addedCustomer);
    }
}

