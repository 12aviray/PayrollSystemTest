// File: Employee.java

public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    // Constructor
    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    // Getters for instance variables
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // Abstract method must be overridden by concrete subclasses
    public abstract double earnings();

    // Concrete method toString, common to all subclasses
    @Override
    public String toString() {
        return String.format("%s %s\nSocial Security Number: %s",
                getFirstName(), getLastName(), getSocialSecurityNumber());
    }
}