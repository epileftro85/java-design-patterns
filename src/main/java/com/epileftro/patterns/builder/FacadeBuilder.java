package com.epileftro.patterns.builder;

class FullPerson {
    public String streetAddress, postcode, city;

    public String companyName, position;

    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class PersonFacadeBuilder {
    protected FullPerson person = new FullPerson();

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonWorkBuilder works() {
        return new PersonWorkBuilder(person);
    }

    public FullPerson build() {
        return person;
    }
}

class PersonAddressBuilder extends PersonFacadeBuilder {
    public PersonAddressBuilder(FullPerson person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String address) {
        person.streetAddress = address;
        return this;
    }

    public PersonAddressBuilder withPostCode(String postCode) {
        person.postcode = postCode;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }
}

class PersonWorkBuilder extends PersonFacadeBuilder {
    public PersonWorkBuilder(FullPerson person) {
        this.person = person;
    }

    public PersonWorkBuilder company(String company) {
        person.companyName = company;
        return this;
    }

    public PersonWorkBuilder position(String position) {
        person.position = position;
        return this;
    }

    public PersonWorkBuilder income(Integer income) {
        person.annualIncome = income;
        return this;
    }
}

public class FacadeBuilder {
    public static void main(String[] args) {
        PersonFacadeBuilder pb = new PersonFacadeBuilder();
        pb.lives()
                .at("St 27")
                .in("Ny")
                .withPostCode("123")
          .works()
                .company("Foo")
                .position("Developer")
                .income(123000);

        System.out.println(pb.build());
    }
}
