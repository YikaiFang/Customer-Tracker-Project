package ui.tools;

import model.Customer;


import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;


/*
Is a new editor menu that edits an existing one with the method pickCustomerOption() which
chooses a customer to edit and the providing option to change for the customer
 */
public class CustomerEditorMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    //EFFECTS: Runs the main customer editor menu
    public CustomerEditorMenu(ArrayList<Customer> customers) {
        this.customers = customers;
        runCustomerEditorMenu();
    }

    //REQUIRES: customer picked must be exactly the same as the name
    //EFFECTS : keeps the editor menu running and until shut down with the corresponding command
    //          and also makes sure all inputs are valid and also picks a customer to be edited
    private void runCustomerEditorMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Welcome to customer editor menu");
        boolean isRunning = true;
        String input;
        Customer pickedCustomer = pickCustomerOption();

        while (isRunning) {
            displayChoices();
            input = this.input.next().toLowerCase();

            if (input.equals("f")) {
                System.out.println("closing customer editor");
                isRunning = false;
            }
            processInput(input, pickedCustomer);
        }

        System.out.println("returning to main menu");
    }

    //REQUIRES: edit a customer there must be a customer available and name must match the customer exactly
    //MODIFIES: customer
    //EFFECTS : edits the different fields of the customer
    private void processInput(String input, Customer pickedCustomer) {
        switch (input) {
            case "n":
                System.out.println("please enter new name");
                pickedCustomer.changeName(this.input.next());
                System.out.println("Customer new name: " + pickedCustomer.getName());
                break;
            case "c":
                new CarEditorMenu(pickedCustomer);
                break;
            case "p":
                System.out.println("please enter new phone number");
                pickedCustomer.changePhoneNumber(this.input.next());
                System.out.println("Customer new phone number: " + pickedCustomer.getPhoneNumber());
                break;
            case "e":
                System.out.println("please enter new email");
                pickedCustomer.changeEmail(this.input.next());
                System.out.println("Customer new email: " + pickedCustomer.getEmail());
                break;
        }
    }


    //EFFECTS: Picks a customer to edit and if there is no match print a message.
    private Customer pickCustomerOption() {
        System.out.println("Please pick customer you would like to edit");
        String chosenCustomer = this.input.next();

        for (Customer c : customers) {
            if (c.getName().equals(chosenCustomer)) {
                return c;
            }
        }
        return null;
    }


    //EFFECTS: displays all the possible options to choose from.
    private void displayChoices() {
        System.out.println("n -> Change name");
        System.out.println("c -> edit cars");
        System.out.println("p -> change phone number");
        System.out.println("e -> change email number");
        System.out.println("f -> closing customer editor");
    }
}
