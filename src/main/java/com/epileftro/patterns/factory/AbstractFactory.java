package com.epileftro.patterns.factory;

import java.util.Scanner;

// Abstract Product
interface Coffee {
    public void prepare();
    public void serve();
}

// Abstract Factory
interface CoffeeFactory {
    public Coffee makeBeverage();
}

// Implementation
class BlackCoffee implements Coffee {
    @Override
    public void prepare() {
        System.out.println("Preparing black Coffee");
    }

    @Override
    public void serve() {
        System.out.println("Here is your black Coffee");
    }
}

// Implementation
class Late implements Coffee {
    @Override
    public void prepare() {
        System.out.println("Preparing Late");
    }

    @Override
    public void serve() {
        System.out.println("Here is your Late");
    }
}

// Implementation
class Cappuccino implements Coffee {
    @Override
    public void prepare() {
        System.out.println("Preparing Cappuccino");
    }

    @Override
    public void serve() {
        System.out.println("Here is your Cappuccino");
    }
}

class BlackFactory implements CoffeeFactory {
    @Override
    public Coffee makeBeverage() {
        return new BlackCoffee();
    }
}

class LateFactory implements CoffeeFactory {
    @Override
    public Coffee makeBeverage() {
        return new Late();
    }
}

class CappuccinoFactory implements CoffeeFactory {
    @Override
    public Coffee makeBeverage() {
        return new Cappuccino();
    }
}

public class AbstractFactory {
    private final static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int i;
        do {
            i = buildFactory();
            switch (i) {
                case 1 -> makeBeverage(new BlackFactory());
                case 2 -> makeBeverage(new LateFactory());
                case 3 -> makeBeverage(new CappuccinoFactory());
                case 4 -> System.out.println("Exiting");
                default -> System.out.println("Beverage not available");
            }
        } while (i != 4);
    }

    public static void makeBeverage(CoffeeFactory factory) {
        Coffee coffee = factory.makeBeverage();
        coffee.prepare();
        coffee.serve();
    }

    public static int buildFactory() {
        System.out.println("\n\nWhich beverage do you want: \n1) Black Coffee \n2) Late \n3) Cappuccino \n4) exit");

        return Integer.parseInt(s.nextLine());
    }
}
