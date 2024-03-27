package ui.gui;

import model.Car;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Car editor menu which can add and remove cars from a customer
public class CarEditorMenuGUI extends JFrame implements ActionListener {

    private JList<String> carInformation;
    private DefaultListModel<String> carInfo;
    private final DefaultListModel<String> customers;
    private JButton addCar;
    private JButton removeCar;
    private final List<Car> cars;
    private JLabel title;
    private JTextField carVinField;
    private JTextField carModelField;
    private JTextField carMakeField;
    private final Customer customer;
    private final int selectedIndex;

    //EFFECTS: constructs a car editor JFrame.
    public CarEditorMenuGUI(int selectedIndex, Customer customer, DefaultListModel<String> customers) {
        this.selectedIndex = selectedIndex;
        this.customer = customer;
        this.customers = customers;
        this.cars = customer.getCars();
        initializeFields();
    }

    //MODIFIES: this
    //EFFECTS: initializes the fields and adds the components to the JFrame.
    private void initializeFields() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        initializeSwing();
        add(addCar);
        add(removeCar);
        add(carInformation);
        add(title);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the car information to the carInfo DefaultListModel.
    private void setCarInformation() {
        for (Car c : cars) {
            carInfo.addElement(c.getYear() + " " + c.getModel() + " " + c.getMake());
        }
    }

    //MODIFIES: this, customer
    //EFFECTS : creates a JOptionPane which asks for the fields of a car to create a new car.
    private void addNewCar() {
        Object[] fields = {"car vin", carVinField, "car model", carModelField, "car make", carMakeField};

        JOptionPane.showConfirmDialog(null, fields, "Please enter:", JOptionPane.OK_CANCEL_OPTION);
        String vin = carVinField.getText();
        String model = carModelField.getText();
        String make = carMakeField.getText();
        Car car = new Car(vin, model, make);
        cars.add(car);
        carInfo.addElement(car.getYear() + " " + car.getModel() + " " + car.getMake());
        customers.set(selectedIndex, informationString(customer));
    }

    //MODIFIES: this, customer
    //EFFECTS: removes the selected car
    private void removeCarFromList(int index) {
        carInfo.remove(index);
        cars.remove(index);
        customers.set(selectedIndex, informationString(customer));
    }

    //MODIFIES: this
    //EFFECTS: initializes the graphical parts of the JFrame
    private void initializeSwing() {
        carInfo = new DefaultListModel<>();
        setCarInformation();
        carInformation = new JList<>(carInfo);
        addCar = new JButton("add");
        addCar.setBounds(25, 25, 200, 50);
        addCar.addActionListener(this);
        removeCar = new JButton("remove");
        removeCar.setBounds(250, 25, 200, 50);
        removeCar.addActionListener(this);
        title = new JLabel();
        title.setText(customer.getName() + "'s cars:");
        title.setFont(new Font("Fira Sans", Font.BOLD, 20));
        title.setBackground(Color.lightGray);
        title.setBounds(25, 100, 450, 25);
        carVinField = new JTextField();
        carMakeField = new JTextField();
        carModelField = new JTextField();
        carInformation.setBounds(25, 125, 450, 300);
        carInformation.setFont(new Font("Fira Sans", Font.PLAIN, 16));
    }

    //EFFECTS: implementation of the actionPerformed method for the ActionListener interface.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addCar)) {
            addNewCar();
        } else if (carInformation.getSelectedIndex() != -1 && e.getSource().equals(removeCar)) {
            removeCarFromList(carInformation.getSelectedIndex());
        }
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
