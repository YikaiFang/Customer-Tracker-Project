package ui.gui;

import model.Car;
import model.Customer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddCustomerMenuGUI extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField carVinField;
    private JTextField carModelField;
    private JTextField carMakeField;
    private DefaultListModel<String> customers;
    private List<Customer> shopCustomers;

    // EFFECTS: constructs the main menu and runs the method mainMenu() and also instantiating a list of customers
    public AddCustomerMenuGUI(DefaultListModel<String> customers, List<Customer> shopCustomers) {
        initializeTextFields();
        this.customers = customers;
        this.shopCustomers = shopCustomers;

        Object[] fields = {
                "name", nameField,
                "email", emailField,
                "phone number", phoneNumberField,
                "car vin", carVinField,
                "car model", carModelField,
                "car make", carMakeField
        };

        JOptionPane.showConfirmDialog(null, fields, "Please enter:", JOptionPane.OK_CANCEL_OPTION);
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String vin = carVinField.getText();
        String model = carModelField.getText();
        String make = carMakeField.getText();
        Customer customer = new Customer(name, phoneNumber, email);
        Car car = new Car(vin, model, make);
        customer.addCar(car);
        this.shopCustomers.add(customer);
        this.customers.addElement(informationString(customer, car));
    }

    private String informationString(Customer customer, Car car) {
        ArrayList<String> cars = new ArrayList<>();
        for (Car c : customer.getCars()) {
            cars.add(car.getYear() + " " + c.getMake() + " " + c.getModel());
        }
        return customer.getName() + " | " + customer.getPhoneNumber() + " | " + customer.getEmail() + " | " + cars;
    }

    private void initializeTextFields() {
        nameField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        carVinField = new JTextField();
        carMakeField = new JTextField();
        carModelField = new JTextField();
    }
}
