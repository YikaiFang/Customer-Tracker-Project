package ui.gui;

import model.Car;
import model.Customer;

import javax.swing.*;
import java.util.ArrayList;

//Creates a pop-up with the information of the selected customer.
public class CustomerEditorMenuGUI extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNumberField;

    //MODIFIES: this, customer
    //EFFECTS: creates a JOptionPane with the customer's personal info to change.
    public CustomerEditorMenuGUI(DefaultListModel<String> customerList, Customer customer, int index) {
        try {
            initializeTextFields(customer);

            Object[] fields = {"name", nameField, "email", emailField, "phone number", phoneNumberField};

            JOptionPane.showConfirmDialog(null, fields, "Please enter changes:", JOptionPane.OK_CANCEL_OPTION);
            String name = nameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            customer.changeName(name);
            customer.changeEmail(email);
            customer.changePhoneNumber(phoneNumber);

            customerList.set(index, informationString(customer));
        } catch (Exception e) {
            //do nothing
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the JTextFields.
    private void initializeTextFields(Customer customer) {
        nameField = new JTextField();
        nameField.setText(customer.getName());
        emailField = new JTextField();
        emailField.setText(customer.getEmail());
        phoneNumberField = new JTextField();
        phoneNumberField.setText(customer.getPhoneNumber());
    }

    //EFFECTS: returns a string of all the personal info of the customer.
    private String informationString(Customer customer) {
        ArrayList<String> cars = new ArrayList<>();
        for (Car c : customer.getCars()) {
            cars.add(c.getYear() + " " + c.getMake() + " " + c.getModel());
        }
        return customer.getName() + " | " + customer.getPhoneNumber() + " | " + customer.getEmail() + " | " + cars;
    }
}
