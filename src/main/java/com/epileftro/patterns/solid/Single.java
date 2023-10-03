package com.epileftro.patterns.solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Single {
    private final List<String> entries = new ArrayList<>();
    private int count = 0;

    public void addEntry(String entry) {
        entries.add(String.format("%d: %s", (++count), entry));
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    /*
     * We will break the Single Responsibility principle if we implement some others functions
     * which are not part of the responsibility of the class, example, load a file
     * or load an url.
     */
    /* public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)){
                out.println(toString());
            }
        }
    } */
}

class Persistence {
    // If we need to save a journal to a file we should put that logic in a different class like this one
    // no inside the same Journal class, because we would be breaking Single Responsibility
    public void saveToFile(Single journal, String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)){
                out.println(journal.toString());
            }
        }
    }
}

class Demo {
    public static void main(String[] args) throws IOException {
        Single j = new Single();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");

        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/tmp/journal.txt";
        p.saveToFile(j, filename, true);

        Runtime.getRuntime().exec("/Applications/Pages.app " + filename);
    }
}
