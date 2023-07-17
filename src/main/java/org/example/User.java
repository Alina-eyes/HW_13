package org.example;

public class User {
    User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    private String firstName;

    public String getLastName() {
        return lastName;
    }

    private String lastName;

    public int getAge() {
        return age;
    }

    private int age;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
