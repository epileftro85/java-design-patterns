package com.epileftro.patterns.solid;

import java.util.List;
import java.util.stream.Stream;

enum Color { RED, GREEN, BLUE }

enum Size { SMALL, MEDIUM, LARGE, HUGE }

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductsFilter {
    /* The next code has some problem, this is duplicated by the properties of the products
     * If for example tomorrow the products has to be filtered by price, we need to create another 2 methods
     * this is directly proportional to the number of properties of products
     * */

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(product -> product.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(product -> product.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
        return products.stream().filter(product -> product.size == size && product.color == color);
    }
}

interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.size == item.size;
    }
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class OpenCloseFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(product -> specification.isSatisfied(product));
    }
}

public class OpenClose {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        ProductsFilter pf = new ProductsFilter();
        System.out.println("Green products (OLD WAY):");
        pf.filterByColor(products, Color.GREEN).forEach(p -> System.out.println(String.format(" - %s is green", p.name)));

        OpenCloseFilter openCloseFilter = new OpenCloseFilter();
        System.out.println("Green products (NEW WAY):");
        // OpenCloseFilter has a property named filter which receive a list of products and a new instance of some Specification
        // It could be Color, Size or AndSpecification, inside the filter function we are using the Specification isSatisfied
        // which compares and return a boolean, if it is satisfied we receive a stream of products
        openCloseFilter.filter(products, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println(String.format(" - %s is green", p.name)));

        System.out.println("Large blue items (NEW WAY):");
        // Here is the same, we receive products and a specification, in this case AndSpecification, which receive size and color in the constructor
        // and override the isSatisfied comparing this 2
        openCloseFilter.filter(products,
                               new AndSpecification<>(
                                       new ColorSpecification(Color.BLUE),
                                       new SizeSpecification(Size.LARGE)
                               )).forEach(p -> System.out.println(String.format(" - %s is large and blue", p.name)));
    }
}
