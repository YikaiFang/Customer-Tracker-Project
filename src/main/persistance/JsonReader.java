package persistance;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Car;
import model.CarShop;
import model.Customer;
import org.json.*;

// Represents a reader that reads car shop from JSON data stored in file
//Taken from the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CarShop read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCarShop(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses car shop from JSON object and returns it
    private CarShop parseCarShop(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CarShop carShop = new CarShop(name);
        addCustomers(carShop, jsonObject);
        return carShop;
    }

    // MODIFIES: carShop
    // EFFECTS: parses customers from JSON object and adds them to carShop
    private void addCustomers(CarShop carShop, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customer");
        for (Object json : jsonArray) {
            JSONObject nextCustomer = (JSONObject) json;
            addCustomer(carShop, nextCustomer);
        }
    }

    // MODIFIES: carShop
    // EFFECTS: parses customer from JSON object and adds it to carShop also parses cars from JSON object
    //          and adds them to the customer.
    private void addCustomer(CarShop carShop, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phone number");
        String email = jsonObject.getString("email");
        Customer customer = new Customer(name, phoneNumber, email);
        JSONArray jsonArray = jsonObject.getJSONArray("cars");
        for (Object json : jsonArray) {
            JSONObject nextCar = (JSONObject) json;
            addCar(customer, nextCar);
        }
        carShop.addCustomer(customer);
    }

    // MODIFIES: customer
    // EFFECTS : parses car and adds it to carShop.
    private void addCar(Customer customer, JSONObject jsonObject) {
        String make = jsonObject.getString("car make");
        String model = jsonObject.getString("car model");
        String vin = jsonObject.getString("vin");
        Car car = new Car(vin, make, model);
        customer.addCar(car);
    }
}
