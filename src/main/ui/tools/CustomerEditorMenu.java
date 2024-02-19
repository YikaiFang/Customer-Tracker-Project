package ui.tools;

import model.Car;
import model.Customer;


import java.util.ArrayList;
import java.util.Scanner;


/*
Is a new editor menu that edits an existing one with the method pickCustomerOption() which
chooses a customer to edit and the providing option to change for the customer
 */
public class CustomerEditorMenu {

    private Scanner input;
    private ArrayList<Customer> customers;
    private ArrayList<String> allCars;

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

        while (isRunning) {
            Customer pickedCustomer = pickCustomerOption();
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
    private void processInput(String input, Customer customerEdited) {
        if (invalidInput()) {
            System.out.println("Invalid input please enter a valid option");
        } else {
            switch (input) {
                case "n":
                    editName(customerEdited);
                    break;
                case "c":
                    new CarEditorMenu(customerEdited);
                    System.out.println(allCars);
                    break;
                case "p":
                    editPhoneNumber(customerEdited);
                    break;
                case "e":
                    editEmail(customerEdited);
                    break;
            }
        }

    }

    //EFFECTS: edits the email of the customer being edited.
    private void editEmail(Customer customerEdited) {
        System.out.println("please enter new email");
        String newEmail = this.input.next();
        customerEdited.changeEmail(newEmail);
        System.out.println("new email: " + newEmail);
    }

    //EFFECTS: edits the phone number of the customer being edited.
    private void editPhoneNumber(Customer customerEdited) {
        System.out.println("please enter new phone number");
        String newPhoneNumber = this.input.next();
        customerEdited.changePhoneNumber(newPhoneNumber);
        System.out.println("new phone number: " + newPhoneNumber);
    }

    //EFFECTS: edits the name of the customer being edited.
    private void editName(Customer customerEdited) {
        System.out.println("Please enter new name");
        String newName = this.input.next();
        customerEdited.changeName(newName);
        System.out.println("new name: " + newName);
    }

    //REQUIRES: !customerChosen.getCars().isEmpty();
    //MODIFIES: this
    //EFFECTS : sets allCars with the name of each car that the customer has
    private void setAllCarsName() {
        Customer customerChosen = pickCustomerOption();
        allCars = new ArrayList<>();
        for (Car c : customerChosen.getCars()) {
            String nameOfCar = c.getYear() + " " + c.getMake() + " " + c.getModel();
            allCars.add(nameOfCar);
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

    private boolean invalidInput() {
        String input = this.input.next().toLowerCase();
        return !input.equals("n") && !input.equals("c") && !input.equals("p")
                && !input.equals("e") && !input.equals("a");
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
