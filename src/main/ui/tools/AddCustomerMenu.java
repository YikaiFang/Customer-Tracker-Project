package ui.tools;

import model.Car;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

/*
Is a customer adder menu where user inputs all the needed information and creates a new customer and adds it
to the list of customers.
 */

public class AddCustomerMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    //EFFECTS: Runs the customer menu
    public AddCustomerMenu(ArrayList<Customer> customers) {
        this.customers = customers;
        runAddCustomerMenu();
    }

    //EFFECTS: Starts the process of asking for information on a customer.
    private void runAddCustomerMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        addNewCustomer();
    }

    //REQUIRES: entered phone number must be a combination of 10 digits 0-9, vin number must be a 17 digit
    //          combination of letters and numbers 1-9, excluding I,O,Q,U, and Z.
    //EFFECTS : asks for all the information about the customer and creates a new customer with all that information
    //          and adds it to the list of customers.
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

