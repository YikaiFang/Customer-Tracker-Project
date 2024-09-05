package ui.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import model.Car;
import model.CarShop;
import model.Customer;
import model.Event;
import model.EventLog;
import persistance.JsonReader;
import persistance.JsonWriter;

/*
Main menu of the application where user is given different choices to choose from each leading to different menus.
 */
public class MainMenuGUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/carShop.json";
    private CarShop carShop;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addCustomerButton;
    private JButton editCustomerButton;
    private JButton editCarsButton;
    private JButton removeCustomerButton;
    private JButton saveButton;
    private JButton loadButton;
    private JList<String> customerList;
    private DefaultListModel<String> customers;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // EFFECTS: constructs the main menu
    public MainMenuGUI() throws FileNotFoundException {
        super("South Auto Center");
        initializeFields();
        initializeLabel();
        initializeGraphics();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    private void printLog(EventLog instance) {
        for (Event e : instance) {
            System.out.println(e);
        }
        //carShop.printEventLog(instance.iterator());
    }

    //EFFECTS: initializes the graphics of the JFrame.
    private void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS : initialize all the fields as well as adds all the components to the JFrame.
    private void initializeFields() {
        carShop = new CarShop("South Auto Center");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        addCustomerButton = new JButton("add customer");
        editCustomerButton = new JButton("edit customer");
        editCarsButton = new JButton("edit customer cars");
        removeCustomerButton = new JButton("remove customer");
        saveButton = new JButton("save");
        loadButton = new JButton("load");
        this.add(addCustomerButton);
        this.add(editCustomerButton);
        this.add(editCarsButton);
        this.add(removeCustomerButton);
        this.add(saveButton);
        this.add(loadButton);
        customers = new DefaultListModel<>();
        customerList = new JList<>(customers);
        customerList.setBounds(300, 25, 700, 675);
        customerList.setFont(new Font("Fira Sans", Font.PLAIN, 16));
        this.add(customerList);
    }

    //MODIFIES: this, customer
    //EFFECTS : edits the selected customer information (name, email, and phone number).
    private void editCustomer(int index) {
        new CustomerEditorMenuGUI(customers, carShop.getCustomers().get(index), index);
    }

    //MODIFIES: this, customer
    //EFFECTS : edits the selected customers cars.
    private void editCars(int index) {
        new CarEditorMenuGUI(index, carShop.getCustomers().get(index), customers);
    }

    //MODIFIES: this, customers
    //EFFECTS : when a customer from the list is selected and remove button is clicked
    //          this will remove the selected customer.
    private void removeCustomer(int index) {
        carShop.removeCustomer(carShop.getCustomers().get(index));
        customers.remove(index);
    }

    //MODIFIES: this
    //EFFECTS : creates a title for the list of customers.
    private void initializeLabel() {
        JLabel customerTitle = new JLabel();
        this.add(customerTitle);
        customerTitle.setBounds(300, 0, 700, 25);
        customerTitle.setFont(new Font("Fira Sans", Font.BOLD, 18));
        customerTitle.setText("Customers");
        customerTitle.setBackground(Color.lightGray);
    }

    // EFFECTS: Opens the add customer menu.
    private void addCustomer() {
        new AddCustomerMenuGUI(customers, carShop);
    }

    // EFFECTS: saves the workroom to file
    private void saveCustomers() {
        try {
            jsonWriter.open();
            jsonWriter.write(carShop);
            jsonWriter.close();

            JOptionPane.showMessageDialog(null,
                    "Saved " + carShop.getName() + " to " + JSON_STORE,
                    "saved",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE,
                    "saved",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadCustomers() {
        try {
            carShop = jsonReader.read();
            savedCustomer();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + carShop.getName() + " from " + JSON_STORE,
                    "loaded",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "loaded",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: displays all the buttons and list on the JFrame and their dimensions.
    private void displayMenu() {
        addCustomerButton.setBounds(0, 0, 300, 50);
        addCustomerButton.addActionListener(this);
        editCustomerButton.setBounds(0, 50, 300, 50);
        editCustomerButton.addActionListener(this);
        editCarsButton.setBounds(0, 100, 300, 50);
        editCarsButton.addActionListener(this);
        removeCustomerButton.setBounds(0, 150, 300, 50);
        removeCustomerButton.addActionListener(this);
        saveButton.setBounds(0, 200, 300, 50);
        saveButton.addActionListener(this);
        loadButton.setBounds(0, 250, 300, 50);
        loadButton.addActionListener(this);
    }

    //EFFECTS: implemented actionPerformed method from ActionListener interface.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addCustomerButton)) {
            addCustomer();
        } else if (customerList.getSelectedIndex() != -1 && e.getSource().equals(editCustomerButton)) {
            editCustomer(customerList.getSelectedIndex());
        } else if (customerList.getSelectedIndex() != -1 && e.getSource().equals(editCarsButton)) {
            editCars(customerList.getSelectedIndex());
        } else if (customerList.getSelectedIndex() != -1 && e.getSource().equals(removeCustomerButton)) {
            removeCustomer(customerList.getSelectedIndex());
        } else if (e.getSource().equals(saveButton)) {
            saveCustomers();
        } else if (e.getSource().equals(loadButton)) {
            loadCustomers();
        }
    }

    //MODIFIES: customers,
    //EFFECTS : adds all the customers and their information into the customers DefaultListModel.
    public void savedCustomer() {
        for (Customer customer : carShop.getCustomers()) {
            String information = customer.getName() + " | " + customer.getPhoneNumber() + " | " + customer.getEmail();
            ArrayList<String> cars = new ArrayList<>();
            for (Car c : customer.getCars()) {
                cars.add(c.getYear() + " " + c.getMake() + " " + c.getModel());
            }
            this.customers.addElement(information + " | " + cars);
        }
    }
}
