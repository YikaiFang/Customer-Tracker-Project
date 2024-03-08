package persistance;

import model.Car;
import model.CarShop;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test suite for the JsonWriter inspirited by JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest {

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void customerSetup() {
        customer1  = new Customer("name", "88888888", "name@gmail.com");
        customer2 = new Customer("something", "777777777", "something@gmail.com");
        Car car1 = new Car("1HGBH41JXMN109186", "ford", "f-150");
        Car car2 = new Car("4Y1SL65848Z411439", "honda", "civic");
        Car car3 = new Car("1HGBH41JXMN109186", "bmw", "x5");

        customer1.addCar(car1);
        customer1.addCar(car2);
        customer2.addCar(car3);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            CarShop carShop = new CarShop("South Auto Center");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            CarShop carShop = new CarShop("South Auto Center");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCarShop.json");
            writer.open();
            writer.write(carShop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCarShop.json");
            carShop = reader.read();
            assertEquals("South Auto Center", carShop.getName());
            assertEquals(0, carShop.getCustomers().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CarShop carShop = new CarShop("South Auto Center");

            carShop.addCustomer(customer1);
            carShop.addCustomer(customer2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCarShop.json");
            writer.open();
            writer.write(carShop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCarShop.json");
            carShop = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}