package model;

import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
A test suite for the car class
 */

public class CarTest {

    private Car testCar;

    @BeforeEach
    public void setup() {
        testCar = new Car("1HGBH41JXMN109186", "Mitsubishi", "Rvr ES");
    }

    @Test
    public void testConstructor() {
        assertEquals(2021, testCar.getYear());
        assertEquals("1HGBH41JXMN109186", testCar.getVin());
        assertEquals("Mitsubishi", testCar.getMake());
        assertEquals("Rvr ES", testCar.getModel());
    }

    @Test
    public void testListsForVin() {
        List<Character> testNumberVinList = new ArrayList<>();
        Collections.addAll(testNumberVinList, '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> testLetterVinList = new ArrayList<>();
        Collections.addAll(testLetterVinList, 'A', 'B', 'C', 'D', 'E', 'F', 'G');
        Collections.addAll(testLetterVinList, 'H', 'J', 'K', 'L', 'M', 'N', 'P');
        Collections.addAll(testLetterVinList, 'R', 'S', 'T', 'V', 'W', 'X', 'Y');

        assertEquals(testNumberVinList, testCar.getNumberVinList());
        assertEquals(testLetterVinList, testCar.getLetterVinList());
    }

    @Test
    public void testVinReaderWithLetter() {
        assertEquals(2021, testCar.getYear());
    }

    @Test
    public void testVinReaderWithNumber() {
        Car testForVinReader = new Car("4Y1SL65848Z411439", "null", "null");
        assertEquals(2008, testForVinReader.getYear());
    }

    @Test
    public void testVinReaderWithY() {
        Car testCarWithYVin = new Car("3FADP4AJ2YM100154", "null", "null");
        assertEquals(2000,testCarWithYVin.getYear());
    }

    @Test
    public void testLetterToVinTwoEdges() {
        assertEquals(-6, testCar.letterToNumber('R'));
        assertEquals(23, testCar.letterToNumber('P'));
    }

    @Test
    public void testLetterToVinWhenVinIsY() {
        assertEquals(0, testCar.letterToNumber('Y'));
    }

    @Test
    public void testLetterToVinRegular() {
        assertEquals(10, testCar.letterToNumber('A'));
        assertEquals(18, testCar.letterToNumber('J'));
    }
}

