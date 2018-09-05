package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals {
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
//        obj = (PersonForEquals) obj;
        if (obj == null) return false;
        if (!(obj instanceof PersonForEquals)) return false;
        return this.yearOfBirth == ((PersonForEquals) obj).yearOfBirth
                && this.name.equals(((PersonForEquals) obj).name)
                && this.hashCode() == obj.hashCode();
        // --end-->
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return this.name.hashCode()+this.yearOfBirth;
        //(int)Integer.valueOf(this.name.codePoints().mapToObj(String::valueOf).reduce("", String::concat).concat(String.valueOf(this.yearOfBirth)));
//        throw new NotImplementedException();
        // --end-->
    }
}
