package catsTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testPlus() {
        // given
        Calculator calculator = new Calculator();

        // when
        int result = calculator.plus(1, 4);

        // then
        assertEquals(5, result);
    }
}