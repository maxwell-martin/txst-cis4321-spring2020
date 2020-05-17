package edu.txstate.mhm85;

public class SalesPerson extends Person implements Speaker {
    public SalesPerson(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    @Override
    public String introduce() {
        return "Hi, my name is " + getFullName() + ". I have something for you.";
    }
}
