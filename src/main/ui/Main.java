package ui;

import ui.gui.MainMenuGUI;
import ui.tools.MainMenu;

import java.io.FileNotFoundException;

// runs the program
public class Main {
    public static void main(String[] args) {
        try {
            new MainMenuGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
