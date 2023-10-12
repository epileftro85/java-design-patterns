package com.epileftro.patterns.solid;

// this principle simply requires that every derived class should be substitutable for its parent class

class Rectangle {
    public int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangule{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    public Square() {}

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

public class LiskovSubstitution {
    // This function violates the Liskov substitution because it only works for
    // Rectangle, if one send a Square which extends from rectangle it doesn't work.
    // Now in order to make it work we should validate if we want to build a square or a rectangle
    // if we are building a square we do not setHeight(10)
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width * 10
        System.out.println(String.format("Expected Area of %d but got %d", width * 10, r.getArea()));
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        Square sq = new Square();
        sq.setHeight(5);
        useIt(sq);
    }
}
