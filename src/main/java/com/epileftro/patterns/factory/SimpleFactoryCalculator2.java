package com.epileftro.patterns.factory;

class Operates {
    private double result;

    // We ensure no constructor use from the outside
    private Operates(double result) {
        this.result = result;
    }

    public static class Factory {
        public static Operates sum(double a, double b) {
            return new Operates(a+b);
        }

        public static Operates multiply(double a, double b) {
            return new Operates(a*b);
        }
    }

    @Override
    public String toString() {
        return "Operates{" +
                "result=" + result +
                '}';
    }
}

public class SimpleFactoryCalculator2 {
    public static void main(String[] args) {
        Operates sum = Operates.Factory.sum(2, 4);
        Operates multiply = Operates.Factory.multiply(4, 5);

        System.out.println(sum);
        System.out.println(multiply);
    }
}