package model.test;

import model.CarShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarShopTest {

    private CarShop testCarShop;

    @BeforeEach
    public void setup() {
        testCarShop = new CarShop("South Auto Center");
    }

    @Test
    public void testConstructor() {
        assertEquals("South Auto Center", testCarShop.getName());
        assertTrue(testCarShop.getCustomers().isEmpty());
    }
}
