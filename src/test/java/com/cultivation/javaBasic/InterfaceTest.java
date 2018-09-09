package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.NameImpl;
import com.cultivation.javaBasic.util.*;
import com.sun.corba.se.spi.ior.ObjectKey;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import javax.naming.Name;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceTest {

    @Test
    void should_support_default_method() {
        InterfaceWithDefaultMethodImpl instance = new InterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is 42";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method() {
        InterfaceWithOverrideDefaultImpl instance = new InterfaceWithOverrideDefaultImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Anime";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method_continued() {
        InterfaceExtendsInterfaceWithDefaultMethod instance = new InterfaceExtendsInterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Game";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_resolve_ambiguity_by_yourself() {
        NameImpl instance = new NameImpl();

        String name = instance.getName();

        assertEquals("Person", name);
    }

    @Test
    void should_be_able_to_clone_an_object () throws CloneNotSupportedException{
        CloneObj originalObj = new CloneObj();
        Object cloneObj = originalObj.clone();

        assertNotEquals(originalObj, (CloneObj) cloneObj);
        System.out.print(Annotation.class.toString());
    }
}

/*
 * - Can you clone an object without a default constructor?
 */
