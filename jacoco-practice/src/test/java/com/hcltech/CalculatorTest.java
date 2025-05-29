package com.hcltech;

import com.hecltech.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CalculatorTest {
    private Calculator calculator;
    @BeforeEach
    void setUp(){
        calculator=new Calculator();
    }
    @Test
    void test_calculate_add(){
        Assertions.assertEquals(2,calculator.calculate(1, 1, '+'));
    }
    @Test
    void test_calculate_subtract(){
        Assertions.assertEquals(0,calculator.calculate(1, 1, '-'));
    }
    @Test
    void test_calculate_multiply(){
        Assertions.assertEquals(1,calculator.calculate(1, 1, '*'));
    }
    @Test
    void test_calculate_divide_1(){
        Assertions.assertEquals(1,calculator.calculate(1, 1, '/'));
    }

    @Test
    void test_calculate_divide_2(){
        Assertions.assertThrows(IllegalArgumentException.class,()->calculator.calculate(1,0,'/'));

    }
    @Test
    void test_calculate_default(){
        Assertions.assertThrows(IllegalArgumentException.class,()->calculator.calculate(1,1,'@'));
    }
    @Test
    void test_dummy(){
        Assertions.assertEquals(1,calculator.dummy(1));
        Assertions.assertEquals(2,calculator.dummy(2));
        Assertions.assertEquals(-1,calculator.dummy(5));


    }
    @Test
    void isEven_test(){
        Assertions.assertEquals(true,calculator.isEven(2));
        Assertions.assertEquals(false,calculator.isEven(3));
    }

}
