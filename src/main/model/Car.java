package model;

/*
A car class with a vin number, make, and model and takes the vin number to determine the year the car was
manufactured.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {

    private final String vin;
    private int year;
    private final String make;
    private final String model;
    private List<Character> numberVinList;
    private List<Character> letterVinList;

    // REQUIRES: vin must be a combination of exactly  17 capital letters and numbers
    // EFFECTS : creates a car with a vin number and using the vin number
    //           to determine the make, model, and year of the car
    public Car(String vin, String make, String model) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        setLetterVinList();
        setNumberVinList();
        vinReader();
    }

    //REQUIRES: tenth character in the vin number must be a number 1-9 or a letter not including I, O, Q, U or Z
    //EFFECTS : takes the tenth character of the vin number and turns produces the corresponding year
    //          from 1994 to 2023.
    private void vinReader() {
        char tenthCharacter = vin.charAt(9);
        for (char c : numberVinList) {
            if (c == tenthCharacter) {
                year = 2000 + Character.getNumericValue(c);
            }
        }
        for (char c : letterVinList) {
            if (c == tenthCharacter) {
                year = 2000 + letterToNumber(c);
            }
        }
    }

    //EFFECTS: a list with all the possible number values for the tenth character of a vin number
    public void setNumberVinList() {
        numberVinList = new ArrayList<>();
        Collections.addAll(numberVinList, '1', '2', '3', '4', '5', '6', '7', '8', '9');

    }

    //EFFECTS: a list with all the possible letter values for the tenth character of a vin number
    public void setLetterVinList() {
        letterVinList = new ArrayList<>();
        Collections.addAll(letterVinList, 'A', 'B', 'C', 'D', 'E', 'F', 'G');
        Collections.addAll(letterVinList, 'H', 'J', 'K', 'L', 'M', 'N', 'P');
        Collections.addAll(letterVinList, 'R', 'S', 'T', 'V', 'W', 'X', 'Y');
    }

    // REQUIRES: char x must be a character from letterVinList or Y.
    // EFFECTS : changes a character to the corresponding amount needed to go from 2000 to the year shown
    //           on the vin number if character is Y then it will return 0.
    public int letterToNumber(char x) {
        List<Character> letterVinListWithoutY = new ArrayList<>();
        Collections.addAll(letterVinListWithoutY, 'A', 'B', 'C', 'D', 'E', 'F', 'G');
        Collections.addAll(letterVinListWithoutY, 'H', 'J', 'K', 'L', 'M', 'N', 'P');
        Collections.addAll(letterVinListWithoutY, 'R', 'S', 'T', 'V', 'W', 'X');

        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 10, 11, 12, 13, 14, 15, 16);
        Collections.addAll(integers, 17, 18, 19, 20, 21, 22, 23);
        Collections.addAll(integers, -6, -5, -4, -3, -2, -1);

        int index = 0;
        for (Character c : letterVinListWithoutY) {
            if (x == c) {
                return integers.get(index);
            }
            index++;
        }
        return 0;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public List<Character> getLetterVinList() {
        return letterVinList;
    }

    public List<Character> getNumberVinList() {
        return numberVinList;
    }
}
