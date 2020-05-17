package edu.txstate.mhm85;

public class Lecturer extends Person implements Speaker {
    public Lecturer(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    @Override
    public String introduce() {
        return "Hi, my name is " + getFullName();
    }
}
