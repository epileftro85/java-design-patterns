package com.epileftro.patterns.builder;

class HtmlFluentBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlFluentBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    // in order to do the builder fluent, we will change the type to the same class
    // and return this;
    public HtmlFluentBuilder addChild(String childName, String childText) {
        HtmlElement element = new HtmlElement(childName, childText);
        root.elements.add(element);

        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

class HtmlFluentPrinter {
    public static void main(String[] args) {
        HtmlFluentBuilder builder = new HtmlFluentBuilder("ul");
        builder
                .addChild("li", "Java")
                .addChild("li", "Builder");
        System.out.println(builder);
    }
}
