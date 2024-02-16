package ui.tools;

import model.Car;

import java.util.ArrayList;
import java.util.Scanner;

// edits and add new cars to a selected customer and takes in a list of the customers current cars.
public class CarEditorMenu {

    private Scanner input;

    public CarEditorMenu(ArrayList<Car> cars) {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runCarEditorMenu();
    }

    private void runCarEditorMenu() {
        boolean isStillRunning = true;
        String command = input.next().toLowerCase();

        while (isStillRunning) {
            if ("c" == command) {
                System.out.println("closing car editor");
                new MainMenu();
            }
        }
    }
}
