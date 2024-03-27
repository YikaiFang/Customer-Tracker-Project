package ui.gui;

import model.Car;
import model.Customer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// Creates a pop-up that asks for information on a customer and adds it.
public class AddCustomerMenuGUI extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField carVinField;
    private JTextField carModelField;
    private JTextField carMakeField;
    private DefaultListModel<String> customers;
    private List<Customer> shopCustomers;

    // EFFECTS: constructs the menu gui
    public AddCustomerMenuGUI(DefaultListModel<String> customers, List<Customer> shopCustomers) {
        try {
            this.customers = customers;
            this.shopCustomers = shopCustomers;
            runAddCustomerMenu();
        } catch (Exception e) {
            //do nothing
        }
    }

    //MODIFIES: customers
    //EFFECTS : produces a JOptionPane and asks for the information of the customer then
    //          adds it to the list.
    private void runAddCustomerMenu() {
        initializeTextFields();

        Object[] fields = {
                "name", nameField,
                "email", emailField,
                "phone number", phoneNumberField,
                "car vin", carVinField,
                "car model", carModelField,
                "car make", carMakeField
        };

        int n = JOptionPane.showConfirmDialog(null, fields, "Please enter:", JOptionPane.OK_CANCEL_OPTION);
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String vin = carVinField.getText();
        String model = carModelField.getText();
        String make = carMakeField.getText();
        if (n == JOptionPane.OK_OPTION) {
            Customer customer = new Customer(name, phoneNumber, email);
            Car car = new Car(vin, model, make);
            customer.addCar(car);
            this.shopCustomers.add(customer);
            this.customers.addElement(informationString(customer));
        }
    }

    //EFFECTS: returns a string value of the customers information and their car information.
    private String informationString(Customer customer) {
        ArrayList<String> cars = new ArrayList<>();
        for (Car c : customer.getCars()) {
            cars.add(c.getYear() + " " + c.getMake() + " " + c.getModel());
        }
        return customer.getName() + " | " + customer.getPhoneNumber() + " | " + customer.getEmail() + " | " + cars;
    }

    //MODIFIES: this
    //EFFECTS: initializes all the JTextFields.
    private void initializeTextFields() {
        nameField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        carVinField = new JTextField();
        carMakeField = new JTextField();
        carModelField = new JTextField();
    }
}
