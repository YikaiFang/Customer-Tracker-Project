package ui.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

import model.CarShop;
import persistance.JsonReader;
import persistance.JsonWriter;
import ui.Main;
import ui.tools.CustomerEditorMenu;

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
    private JButton removeCustomerButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton surpriseButton;
    private JList<String> customerList;
    private DefaultListModel<String> customers;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // EFFECTS: constructs the main menu and runs the method mainMenu() and also instantiating a list of customers
    public MainMenuGUI() throws FileNotFoundException {
        super("South Auto Center");
        initializeFields();
        initializeSearchBar();
        initializeGraphics();
    }


    private void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        displayMenu();
    }

    private void initializeFields() {
        carShop = new CarShop("South Auto Center");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        addCustomerButton = new JButton("add customer");
        editCustomerButton = new JButton("edit customer menu");
        removeCustomerButton = new JButton("remove customer");
        saveButton = new JButton("save");
        loadButton = new JButton("load");
        surpriseButton = new JButton("press for suprise");
        this.add(addCustomerButton);
        this.add(editCustomerButton);
        this.add(removeCustomerButton);
        this.add(saveButton);
        this.add(loadButton);
        this.add(surpriseButton);
        customers = new DefaultListModel<>();
        customerList = new JList<>(customers);
        customerList.setBounds(300, 25, 700, 675);
        customerList.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        this.add(customerList);
    }

    //EFFECTS: opens the customer editor menu
    private void editCustomer() {
        new CustomerEditorMenu(carShop.getCustomers());
    }

    //REQUIRES: at least two customers must be present
    //EFFECTS : removes a customer by inputting the customers name in this current console ui,
    //          however would like to change this to a button when implementing a GUI cannot remove all
    //          the customers.
    private void removeCustomer() {

    }

    private void initializeSearchBar() {
        JTextField searchBar = new JTextField();
        this.add(searchBar);
        searchBar.setBounds(300, 0, 700, 25);
        searchBar.setFont(new Font("Fira Sans", Font.PLAIN, 16));
        searchBar.setText("search for customer");
        searchBar.setBackground(Color.lightGray);
    }

    // EFFECTS: Opens the add customer menu and prints out the list of all customers names.
    private void addCustomer() {
        new AddCustomerMenuGUI(customers, carShop.getCustomers());
    }

    // EFFECTS: saves the workroom to file
    private void saveCustomers() {
        try {
            jsonWriter.open();
            jsonWriter.write(carShop);
            jsonWriter.close();
            System.out.println("Saved " + carShop.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadCustomers() {
        try {
            carShop = jsonReader.read();
            System.out.println("Loaded " + carShop.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void surprise() {
        JFrame frame = new JFrame();
        ImageIcon imageIcon =
                new ImageIcon("C:/Users/super/IdeaProjects/project_h4p6l/src/main/gregor-kiczales-2023-profile.jpg");
        JLabel label = new JLabel(imageIcon);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width) / 2;
        int y = (screenSize.height) / 2;

        label.setText("Trust the natural recursion");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-350);
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setForeground(Color.RED);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        frame.setResizable(false);
        frame.add(label);
        frame.setLocation(x, y);
        frame.setVisible(true);

        System.out.println(imageIcon.getIconHeight() + " " + imageIcon.getIconWidth());

    }

    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays all the choices with the format input key -> what it does.
    private void displayMenu() {
        addCustomerButton.setSize(300, 50);
        addCustomerButton.setBounds(0, 0, 300, 50);
        addCustomerButton.addActionListener(this);
        editCustomerButton.setBounds(0, 50, 300, 50);
        editCustomerButton.setSize(300, 50);
        editCustomerButton.addActionListener(this);
        removeCustomerButton.setSize(300, 50);
        removeCustomerButton.setBounds(0, 100, 300, 50);
        removeCustomerButton.addActionListener(this);
        saveButton.setSize(300, 50);
        saveButton.setBounds(0, 150, 300, 50);
        saveButton.addActionListener(this);
        loadButton.setSize(300, 50);
        loadButton.setBounds(0, 200, 300, 50);
        loadButton.addActionListener(this);
        surpriseButton.setBounds(0, 250, 300, 450);
        surpriseButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addCustomerButton)) {
            addCustomer();
        } else if (e.getSource().equals(editCustomerButton)) {
            editCustomer();
        } else if (e.getSource().equals(removeCustomerButton)) {
            removeCustomer();
        } else if (e.getSource().equals(saveButton)) {
            saveCustomers();
        } else if (e.getSource().equals(loadButton)) {
            loadCustomers();
        } else if (e.getSource().equals(surpriseButton)) {
            surprise();
        }
    }
}
