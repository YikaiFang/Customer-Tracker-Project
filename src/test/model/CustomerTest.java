package model;

import model.Car;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
A test suite for the customer class
 */

class CustomerTest {

    private Customer testCustomer;
    private Car testCar1;
    private Car testCar2;
    private ArrayList<Customer> testCustomerList;

    @BeforeEach
    public void setup() {
        testCustomer = new Customer("chaelyn apquiz", "5875006294", "chaelynapquiz@gmail.com");
        testCar1 = new Car("1HGBH41JXMN109186", "Mitsubishi", "Rvr ES");
        testCar2 = new Car("3FADP4AJ2YM100154", "Tesla", "X");
        testCustomerList = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertEquals("chaelyn apquiz", testCustomer.getName());
        assertEquals("5875006294", testCustomer.getPhoneNumber());
        assertEquals("chaelynapquiz@gmail.com", testCustomer.getEmail());
        assertTrue(testCustomer.getCars().isEmpty());
    }

    @Test
    public void testAddCustomer() {
        testCustomer.addCar(testCar1);
        assertEquals(1, testCustomer.getCars().size());
        assertEquals(testCar1, testCustomer.getCars().get(0));
        testCustomer.addCar(testCar2);
        assertEquals(2, testCustomer.getCars().size());
        assertEquals(testCar2, testCustomer.getCars().get(1));
    }

    @Test
    public void testRemoveCar() {
        testCustomer.addCar(testCar1);
        testCustomer.addCar(testCar2);
        testCustomer.removeCar(testCar1);
        assertEquals(1, testCustomer.getCars().size());
        assertEquals(testCar2, testCustomer.getCars().get(0));
        testCustomer.removeCar(testCar2);
        assertTrue(testCustomer.getCars().isEmpty());
    }

    @Test
    public void testChangeName() {
        testCustomer.changeName("chaebae");
        assertEquals("chaebae", testCustomer.getName());
    }

    @Test
    public void testChangeEmail() {
        testCustomer.changeEmail("fernandoalonsofan@gmail.com");
        assertEquals("fernandoalonsofan@gmail.com", testCustomer.getEmail());
    }

    @Test
    public void testChangePhoneNumber() {
        testCustomer.changePhoneNumber("56324336");
        assertEquals("56324336", testCustomer.getPhoneNumber());
    }

    @Test
    public void testFindCustomerNoMatch() {
        assertEquals(null, testCustomer.findCustomer(testCustomerList, "chaelyn apquiz"));
    }

    @Test
    public void testFindCustomerWithMatchListSizeOne() {
        testCustomerList.add(testCustomer);
        assertEquals(testCustomer, testCustomer.findCustomer(testCustomerList, "chaelyn apquiz"));
    }

    @Test
    public void testFindCustomerWithMatchListSizeTwo() {
        Customer nullCustomer = new Customer("something", "long number", "something@gmail.com");
        testCustomerList.add(nullCustomer);
        testCustomerList.add(testCustomer);
        assertEquals(testCustomer, testCustomer.findCustomer(testCustomerList, "chaelyn apquiz"));
    }

}