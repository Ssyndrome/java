package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable{
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        // TODO: please modify the following code to pass the test
        // <--start
        if (obj == this) return true;
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (!this.getName().equals(((PersonForEquals) obj).getName())) {
            return false;
        }
        if (this.yearOfBirth == ((PersonForEquals) obj).yearOfBirth) {
            if (this.hashCode() == obj.hashCode()) {
                return true;
            }
        }
        return false;
        // --end-->
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return this.yearOfBirth + name.codePoints().sum();
        // --end-->
    }

    @Override
    public int compareTo(Object otherObject) throws NullPointerException{

        PersonForEquals otherPerson = (PersonForEquals) otherObject;

        int nameCompare = this.name.compareTo(otherPerson.name);
        if ( nameCompare == 0) {
            return this.yearOfBirth - otherPerson.yearOfBirth;
        }
        return nameCompare;
    }
}
