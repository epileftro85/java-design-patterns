package com.epileftro.patterns.builder;

import java.util.ArrayList;
import java.util.Collections;

public class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }
        for (HtmlElement e : elements) {
            sb.append(e.toStringImpl(indent + 1));
        }

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public void addChild(String childName, String childText) {
        HtmlElement element = new HtmlElement(childName, childText);
        root.elements.add(element);
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

class HtmlNewPrinter {
    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder("ol");
        builder.addChild("li", "Java");
        builder.addChild("li", "Builder");
        System.out.println(builder);
    }
}

class HtmlOldPinter {
    public static void main(String[] args) {
        // We could create a string like the next code
        String hello = "hello";
        System.out.println(String.format("<p>%s</p>", hello));

        // Or we could use the StringBuilder in order to create our html,
        // but it is not general
        String[] words = { "Hello", "World" };
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb);
    }
}