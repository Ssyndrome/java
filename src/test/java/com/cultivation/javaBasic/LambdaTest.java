package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {

    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = this::instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = LambdaTest::staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = ArrayList::new;
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    @Test
    void should_return_right_value_using_lambda() {
        int expectedNumber = 99;
        IntSupplier intSupplier = () -> 99;

        assertEquals(expectedNumber, intSupplier.getAsInt());

    }

    @Test
    void should_return_right_char_value_using_lambda() {
        CharSupplier charSupplier = () -> 'x';

        char expectedChar = 'x';

        assertEquals(expectedChar, charSupplier.getAsChar());
    }

    @Test
    void should_return_what_get() {
        IntFunction intFunction = (a) -> a;

        int expectedNumber = 5;

        assertEquals(expectedNumber, intFunction.apply(5));
    }

    @Test
    void should_use_interface_to_add_two_integer() {
        int expected = 18;

        BIfunction bIfunction = (a, b) -> a + b;

        assertEquals(expected, bIfunction.apply(9, 9));
    }

    @Test
    void should_exchange_the_value_of_an_array_of_objects() {
        BIConsumer biConsumer = (array) -> {
            if (array.length >= 2) {
                Object zero = array[1];
                array[1] = array[0];
                array[0] = zero;
            }
        };

        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        Object[] oneElement = new Object[]{object1};
        Object[] twoElement = new Object[]{object1, object2};
        Object[] threeElement = new Object[]{object1, object2, object3};

        biConsumer.accept(oneElement);
        biConsumer.accept(twoElement);
        biConsumer.accept(threeElement);

        assertArrayEquals(new Object[]{object1}, oneElement);
        assertArrayEquals(new Object[]{object2, object1}, twoElement);
        assertArrayEquals(new Object[]{object2, object1, object3}, threeElement);
    }

    @Test
    void should_get_sum_of_int_array() {
        SummatorFunction summer = (ints) -> {
            int sum = 0;
            if (ints == null) return sum;
            for (int anInt : ints) {
                sum += anInt;
            }
            return sum;
        };

        int[] nullArray = null;
        int[] oneElementArray = new int[]{1};
        int[] fiveElementArray = new int[]{1, 2, 3, 4, 5};

        assertEquals(0, summer.apply(nullArray));
        assertEquals(1, summer.apply(oneElementArray));
        assertEquals(15, summer.apply(fiveElementArray));
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
