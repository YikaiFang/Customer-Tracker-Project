package ui.tools;

import model.Car;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/*
Edits and add new cars to a selected customer and takes in a list of the customers current cars.
 */
public class CarEditorMenu {

    private Scanner input;
    private final Customer editedCustomer;

    //EFFECTS: Starts the car editor menu
    public CarEditorMenu(Customer editedCustomer) {
        this.editedCustomer = editedCustomer;
        runCarEditorMenu();
    }

    //EFFECTS: keeps the car editor menu running.
    private void runCarEditorMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        boolean isStillRunning = true;
        String command;

        while (isStillRunning) {
            displayChoices();
            command = input.next().toLowerCase();

            if ("f".equals(command)) {
                isStillRunning = false;

            } else {
                processInput(command);
            }
        }
        printAllCars();
        System.out.println("closing car editor");
    }

    // EFFECTS: process the next command of the use if they do not match any of
    //          the choices nothing happens
    private void processInput(String input) {
        switch (input) {
            case "a":
                addCar();
                break;
            case "r":
                removeCar();
                break;
            default:
                System.out.println("That option is not valid please pick a valid option.");
                break;
        }
    }

    //REQUIRES: !addedCustomer.getCars().isEmpty();
    //MODIFIES: this
    //EFFECTS : removes a chosen car from the list of cars in a customer
    private void removeCar() {
        System.out.println("Please enter vin:");
        String vin = input.next();
        ArrayList<Car> customerCars = editedCustomer.getCars();
        String removedCar = null;
        for (int i = 0; i < customerCars.size(); i++) {
            if (customerCars.get(i).getVin().equals(vin)) {
                removedCar = customerCars.get(i).getYear() + " " + customerCars.get(i).getMake() + " "
                        + customerCars.get(i).getModel();
                customerCars.remove(customerCars.get(i));
            }
        }
        System.out.println("successfully removed " + removedCar);
    }

    //MODIFIES: this
    //EFFECTS : asks for the information of a car and creates a new car which is then added to the customer
    //          being edited
    private void addCar() {
        System.out.println("Insert vin: ");
        String vin = input.next();
        System.out.println("Make of the car: ");
        String make = input.next();
        System.out.println("Model of the car: ");
        String model = input.next();
        Car addedCar = new Car(vin, make, model);
        editedCustomer.addCar(addedCar);
        System.out.println("Added new car " + addedCar.getYear() +  " " + make +  " " + model
                            + " to " + editedCustomer.getName());
    }

    //EFFECTS: print all the cars of the edited customer.
    public void printAllCars() {
        ArrayList<String> customerCars = new ArrayList<>();
        for (Car c : editedCustomer.getCars()) {
            customerCars.add(c.getYear() + " " + c.getMake() + " " + c.getModel());
        }
        System.out.println(customerCars);
    }

    //EFFECTS: displays the possible choices for the user.
    private void displayChoices() {
        System.out.println("a -> add car");
        System.out.println("r -> remove car");
        System.out.println("f -> finished editing cars");
    }
}
