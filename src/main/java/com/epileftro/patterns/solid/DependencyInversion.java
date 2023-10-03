package com.epileftro.patterns.solid;

// High-level modules should not depend on low-level modules. Both should depend on abstractions.
// Abstractions should not depend on details. Details should depend on abstractions.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

enum Relationship {PARENT, CHILD, SIBLING}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

interface RelationBrowser {
    List<Person> findAllChildrenOf(String name);
}

// This is a low-level module due this is a data related layer
// this is a bridge between data storage and data provided
/* class Relationships {
    private final List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
} */

// Here we implement a better implementation of the logic with an abstracted interface
class Relationships implements RelationBrowser{
    private final List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                 .filter(x -> Objects.equals(x.getValue0().name, name)
                         && x.getValue1() == Relationship.PARENT)
                 .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

// This is a high-level module due this class interact from the user to a low level
// data storage, like a controller or a service.
class Research {
    // This breaks the principle because depends on a low level module
    // If the low level change, this function must be changed too.
    // How do we know this is breaking principle? because this is a high level module
    // And receives a low level module as a dependency in the constructor.
    /*public Research(Relationships relationships) {
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
        relations.stream()
                 .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
                 .forEach(ch -> System.out.printf("John has a child named %s%n", ch.getValue2().name));
    }*/

    // This one es depending on an abstraction of RelationBrowser, because this RelationBrowser is implemented
    // in RelationShips class, we are using this unique implementation. Here we will use findAllChildrenOf.
    public Research(RelationBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");

        for (Person child : children) {
            System.out.println(String.format("John has a child called %s", child.name));
        }
    }
}

public class DependencyInversion {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Andres");
        Person child2 = new Person("Juan");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}
