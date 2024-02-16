package ui.tools;

import model.Customer;


import java.util.ArrayList;
import java.util.Scanner;


// Is a new editor menu that edits an existing one with the method pickCustomerOption() which
// chooses a customer to edit and the providing option to change for the customer
public class CustomerEditorMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    public CustomerEditorMenu(ArrayList<Customer> customers) {
        this.customers = customers;
        runCustomerEditorMenu();
    }

    private void runCustomerEditorMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Welcome to customer editor menu");
        Boolean isRunning = true;
        String input;

        while (isRunning) {
            displayChoices();
            input = this.input.next().toLowerCase();

            if (input.equals("c")) {
                System.out.println("canceling customer editor");
                isRunning = false;
            } else {
                processInput(input);
            }
        }

        System.out.println("returning to main menu");
        new MainMenu();
    }

    private void processInput(String input) {
        Customer customerEdited = null;
        pickCustomerOption();
        switch (input) {
            case "n":
                System.out.println("please enter new name");
                customerEdited.changeName(this.input.next());
                System.out.println("Customer new name: " + customerEdited.getName());
                break;
            case "1":
                new CarEditorMenu(customerEdited.getCars());
                break;
            case "p":
                System.out.println("please enter new phone number");
                customerEdited.changePhoneNumber(this.input.next());
                System.out.println("Customer new phone number: " + customerEdited.getPhoneNumber());
                break;
            case "e":
                System.out.println("please enter new email");
                customerEdited.changeEmail(this.input.next());
                System.out.println("Customer new email: " + customerEdited.getEmail());
                break;
        }
    }

    private void pickCustomerOption() {
        System.out.println("Please pick customer you would like to edit");
        String chosenCustomer = this.input.next();
        Customer customerEdited = null;
        for (Customer c : customers) {
            if (c.getName().equals(chosenCustomer)) {
                customerEdited = c;
            }
        }
    }

    private void displayChoices() {
        System.out.println("n -> Change name");
        System.out.println("1 -> edit cars");
        System.out.println("p -> change phone number");
        System.out.println("e -> change email number");
        System.out.println("c -> cancel customer editor");
    }
}
