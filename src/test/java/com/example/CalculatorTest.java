package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Calculatorクラスのテスト
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    void testAdd() {
        // 2 + 3 = 5 を検証
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
        assertEquals(0, calculator.add(-1, 1), "-1 + 1 should equal 0");
        assertEquals(100, calculator.add(50, 50), "50 + 50 should equal 100");
    }
    
    @Test
    void testSubtract() {
        // 10 - 4 = 6 を検証
        assertEquals(6, calculator.subtract(10, 4), "10 - 4 should equal 6");
        assertEquals(-5, calculator.subtract(5, 10), "5 - 10 should equal -5");
        assertEquals(0, calculator.subtract(5, 5), "5 - 5 should equal 0");
    }
    
    @Test
    void testMultiply() {
        // 3 * 4 = 12 を検証
        assertEquals(12, calculator.multiply(3, 4), "3 * 4 should equal 12");
        assertEquals(0, calculator.multiply(5, 0), "5 * 0 should equal 0");
        assertEquals(-6, calculator.multiply(2, -3), "2 * -3 should equal -6");
    }
    
    @Test
    void testDivide() {
        // 10 / 2 = 5.0 を検証
        assertEquals(5.0, calculator.divide(10, 2), 0.001, "10 / 2 should equal 5.0");
        assertEquals(2.5, calculator.divide(5, 2), 0.001, "5 / 2 should equal 2.5");
        assertEquals(-3.0, calculator.divide(-6, 2), 0.001, "-6 / 2 should equal -3.0");
    }
    
    @Test
    void testDivideByZero() {
        // 0で割った場合の例外を検証
        assertThrows(IllegalArgumentException.class, 
            () -> calculator.divide(10, 0), 
            "0で割った場合はIllegalArgumentExceptionがスローされるべき");
    }
}

