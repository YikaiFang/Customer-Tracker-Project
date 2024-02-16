package ui.tools;

import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

// is a customer adder menu where user inputs all the needed information excluding cars of a customer
public class AddCustomerMenu {

    private Scanner input;
    private ArrayList<Customer> customers;

    public AddCustomerMenu(ArrayList<Customer> customers) {
        this.customers = customers;
        runAddCustomerMenu();
    }

    private void runAddCustomerMenu() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        addNewCustomer();
    }

    private void addNewCustomer() {
        System.out.println("Please enter name: ");
        String name = input.next();
        System.out.println("Please enter emails: ");
        String email = input.next();
        System.out.println("Please enter phone number: ");
        String phoneNumber = input.next();
        Customer addedCustomer = new Customer(name, email, phoneNumber);
        customers.add(addedCustomer);
        //new CarEditorMenu(addedCustomer.getCars());
    }
}

