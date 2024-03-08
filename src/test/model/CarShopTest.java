package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarShopTest {

    private CarShop testCarShop;
    private Customer customer1;

    @BeforeEach
    public void setup() {
        testCarShop = new CarShop("South Auto Center");
        customer1 = new Customer("name", "88888888", "name@gmail.com");
    }

    @Test
    public void testConstructor() {
        assertEquals("South Auto Center", testCarShop.getName());
        assertTrue(testCarShop.getCustomers().isEmpty());
    }

    @Test
    public void testAddCustomer() {
        assertTrue(testCarShop.getCustomers().isEmpty());
        testCarShop.addCustomer(customer1);
        assertTrue(testCarShop.getCustomers().contains(customer1));
        assertEquals("name", testCarShop.getCustomers().get(0).getName());
        assertEquals("88888888", testCarShop.getCustomers().get(0).getPhoneNumber());
        assertEquals("name@gmail.com", testCarShop.getCustomers().get(0).getEmail());
        assertTrue(testCarShop.getCustomers().get(0).getCars().isEmpty());
    }
}
