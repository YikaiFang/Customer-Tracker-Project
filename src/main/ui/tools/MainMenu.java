package ui.tools;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import model.Car;
import model.Customer;

/*
Main menu of the application where user is given different choices to choose from each leading to different menus.
 */

public class MainMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    // EFFECTS: constructs the main menu and runs the method mainMenu() and also instantiating a list of customers
    public MainMenu() {
        customers = new ArrayList<>();
        runMainMenu();
    }

    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: actually starts the main menu and shows all the options that user can pick from.
    public void runMainMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        boolean isStillRunning = true;
        String input;

        while (isStillRunning) {
            printAllCustomers();
            displayMenu();
            input = this.input.next().toLowerCase();

            if (input.equals("q")) {
                isStillRunning = false;
            } else {
                processInput(input);
            }
        }

        System.out.println("Closing application");

    }

    // EFFECTS: Processes the input of the user if it doesn't match any of the given options nothing will happen.
    private void processInput(String input) {
        switch (input) {
            case "a":
                addCustomer();
                break;
            case "r":
                removeCustomer();
                break;
            case "e":
                editCustomer();
                break;
            case "s":
                searchForCustomer();
                break;
            default:
                System.out.println("Invalid option, must choose one of the options listed above.");
                break;
        }
    }

    //REQUIRES: !customers.isEmpty();
    //EFFECTS : Finds the customer in the list of all the customers with the name and prints out
    //          all the information about that customer.
    private void searchForCustomer() {
        Customer nullCustomer = new Customer(null, null, null);
        System.out.println("Please enter customer name");
        String name = input.next();
        Customer chosenCustomer = nullCustomer.findCustomer(customers, name);
        ArrayList<String> customerCars = new ArrayList<>();
        for (Car car : chosenCustomer.getCars()) {
            customerCars.add(car.getYear() + " " + car.getMake() + " " + car.getModel());
        }
        System.out.println("Result: " + chosenCustomer.getName() + " | " + chosenCustomer.getEmail()
                + " | " + chosenCustomer.getPhoneNumber() + " | " + customerCars);
        displayMenu();
        processInput(input.next());
    }

    //EFFECTS: opens the customer editor menu
    private void editCustomer() {
        new CustomerEditorMenu(customers);
    }

    //REQUIRES: at least two customers must be present
    //EFFECTS : removes a customer by inputting the customers name in this current console ui,
    //          however would like to change this to a button when implementing a GUI cannot remove all
    //          the customers.
    private void removeCustomer() {
        String removedCustomer = "";
        System.out.println("Please enter name of customer you would like to remove: ");
        String customerName = input.next();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(customerName)) {
                removedCustomer = customers.get(i).getName();
                customers.remove(customers.get(i));
            }
        }

        if (Objects.equals(removedCustomer, "")) {
            System.out.println("No customer with name: " + customerName);
        } else {
            System.out.println("Removed customer: " + removedCustomer);
        }
    }

    // EFFECTS: Opens the add customer menu and prints out the list of all customers names.
    private void addCustomer() {
        new AddCustomerMenu(customers);
    }

    // REQUIRES: customers is not an empty list
    // EFFECTS : prints out a list of all the customers with their personal info and a list of their cars
    public void printAllCustomers() {
        System.out.println("All customers: ");

        for (Customer c : customers) {
            ArrayList<String> cars = new ArrayList<>();
            for (Car car : c.getCars()) {
                cars.add(car.getYear() + " " + car.getMake() + " " + car.getModel());
            }
            System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber() + " | " + cars);
        }
    }

    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays all the choices with the format input key -> what it does.
    private void displayMenu() {
        System.out.println("Choices: ");
        System.out.println("a -> Add new customer");
        System.out.println("r -> Remove customer");
        System.out.println("e -> edit a customer");
        System.out.println("s -> search for customer");
        System.out.println("q -> quit application");
    }
}
