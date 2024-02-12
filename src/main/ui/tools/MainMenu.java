package ui.tools;

import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    private Scanner input;

    public MainMenu() {
        runMainMenu();
    }

    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    public void runMainMenu() {
        Boolean isStillRunning = true;
        String input;

        while (isStillRunning) {
            displayMenu();
            input = this.input.next().toLowerCase();

            if (input.equals("q")) {
                isStillRunning = false;
            } else {
                processInput(input);
            }
        }

    }

    private void processInput(String input) {
    }

    private void displayMenu() {
    }
}
