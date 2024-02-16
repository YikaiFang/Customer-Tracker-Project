package ui.tools;

import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;

// main menu of the application where user is given different choices to choose from each leading to a different menu
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
        Boolean isStillRunning = true;
        String input = null;

        while (isStillRunning) {
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
        if (input.equals("a")) {
            addCustomer();
        } else if (input.equals("r")) {
            removeCustomer();
        } else if (input.equals("e")) {
            editCustomer();
        }
    }

    //EFFECTS: opens the customer editor menu
    private void editCustomer() {
        new CustomerEditorMenu(customers);
    }

    //EFFECTS: removes a customer by inputing the customers name in this current console ui,
    //         however would like to change this to a button when implementing a GUI.
    private void removeCustomer() {
        String removedCustomer = null;
        for (Customer c : customers) {
            if (c.getName().equals(this.input.next())) {
                removedCustomer = c.getName();
                customers.remove(c);
            }
        }
        if (removedCustomer == null) {
            System.out.println("No customer with name: " + input);
        } else {
            System.out.println("Removed customer: " + removedCustomer);
        }
    }

    // EFFECTS: Opens the add customer menu and prints out the list of all customers names.
    private void addCustomer() {
        new AddCustomerMenu(customers);
        ArrayList<String> customerNames = new ArrayList<>();
        for (Customer c : customers) {
            customerNames.add(c.getName());
        }
        System.out.println("all customers: " + customerNames);
    }

    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays all the choices with the format input key -> what it does.
    private void displayMenu() {
        System.out.println("Choices: ");
        System.out.println("a -> Add new customer");
        System.out.println("r -> Remove customer");
        System.out.println("e -> edit a customer");
        System.out.println("q -> quit application");
    }
}
