package catsTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testPlus() {
        Calculator calculator = new Calculator();

        int result = calculator.plus(1, 4);

        assertEquals(5, result);
    }

}