package com.epileftro.patterns.factory;

enum OPERATIONS {
    MULTIPLY, SUM, SUBTRACT
}

class Operation {
    private double result;

    Operation(double a, double b, OPERATIONS op) {
        switch (op) {
            case SUM -> result = a+b;
            case SUBTRACT -> result = a-b;
            case MULTIPLY -> result = a*b;
            default -> {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Operators{" +
                "result=" + result +
                '}';
    }
}
public class SimpleFactoryCalculator {
    public static void main(String[] args) {
        Operation sum = new Operation(2, 3, OPERATIONS.SUM);
        Operation multiply = new Operation(4, 4, OPERATIONS.MULTIPLY);

        System.out.println(sum);
        System.out.println(multiply);
    }
}
