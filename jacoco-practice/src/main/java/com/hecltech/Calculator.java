package com.hecltech;

public class Calculator {

    public int calculate(int a,int b, char operator){
        return switch (operator) {
            case '+' -> add(a, b);
            case '-' -> subtract(a, b);
            case '*' -> multiply(a, b);
            case '/' -> divide(a, b);
            default -> throw new IllegalArgumentException("Invalid Operator " + operator);
        };
    }

    private int add(int a, int b){
        return a+b;
    }

    private int subtract(int a,int b){
     return a-b;
    }

    private int multiply(int a,int b){
        return a*b;
    }
    private int divide(int a,int b){
        if(b==0) throw new IllegalArgumentException("Second Operand cannot be zero.");
        return a/b;
    }

    public boolean isEven(int n){
        return n%2==0;
    }

    public int dummy(int n){
        return switch (n){
            case 1 ->1;
            case 2 ->2;
            default -> -1;
        };
    }
}


