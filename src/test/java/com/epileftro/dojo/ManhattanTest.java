package com.epileftro.dojo;

import com.epileftro.dojo.Manhattan.model.Point;
import com.epileftro.dojo.Manhattan.utils.Calculations;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManhattanTest {
    /*    @Test
    public void test_positive_values() {
        Point a = new Point(2, 3);
        Point b = new Point(5, 7);
        int result = ManhattanCalculation.manhattanCal(a, b);
        assertEquals(7, result);
    }

    @Test
    public void test_negative_values() {
        Point a = new Point(-2, -3);
        Point b = new Point(-5, -7);
        int result = ManhattanCalculation.manhattanCal(a, b);
        assertEquals(7, result);
    }

    @Test
    public void test_mixed_values() {
        Point a = new Point(-2, 3);
        Point b = new Point(5, -7);
        int result = ManhattanCalculation.manhattanCal(a, b);
        assertEquals(17, result);
    }*/

    @ParameterizedTest
    @CsvSource({"7, 2, 3, 5, 7",
            "7, -2, -3, -5, -7",
            "17, -2, 3, 5, -7"
    })
    void manhattanParameterizedTest(int expected, int x1, int y1, int x2, int y2) {
        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);

        assertEquals(expected, Calculations.manhattanCal(a, b));
    }
}
