package persistance;

import model.CarShop;
import model.Customer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

// test suite for the JsonReader inspirited by JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CarShop carShop = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCarShopg() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCarShop.json");
        try {
            CarShop carShop = reader.read();
            assertEquals("South Auto Center", carShop.getName());
            assertEquals(0, carShop.getCustomers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCarShop() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCarShop.json");
        try {
            CarShop carShop = reader.read();
            assertEquals("South Auto Center", carShop.getName());
            List<Customer> customers = carShop.getCustomers();
            assertEquals(2, customers.size());
            assertEquals("f-150", customers.get(0).getCars().get(0).getModel());
            assertEquals("ford", customers.get(0).getCars().get(0).getMake());
            assertEquals("1HGBH41JXMN109186", customers.get(0).getCars().get(0).getVin());
            assertEquals(2021, customers.get(0).getCars().get(0).getYear());
            assertEquals("civic", customers.get(0).getCars().get(1).getModel());
            assertEquals("honda", customers.get(0).getCars().get(1).getMake());
            assertEquals("4Y1SL65848Z411439", customers.get(0).getCars().get(1).getVin());
            assertEquals(2008, customers.get(0).getCars().get(1).getYear());
            assertEquals("x5", customers.get(1).getCars().get(0).getModel());
            assertEquals("bmw", customers.get(1).getCars().get(0).getMake());
            assertEquals("1HGBH41JXMN109186", customers.get(1).getCars().get(0).getVin());
            assertEquals(2021, customers.get(1).getCars().get(0).getYear());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}