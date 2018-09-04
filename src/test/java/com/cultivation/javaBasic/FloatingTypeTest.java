package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 3333333333333333333333333333333333332.75f;
        final int integer = (int)floatingPointNumber;

        final short integerShort = (short) floatingPointNumber;
        final  byte integerByte = (byte) floatingPointNumber;
        final long integerLong = (long)floatingPointNumber;

        final double byteToDouble = integerByte;
        final double shortToDouble = integerShort;
        final double longToDouble = integerLong;
        final double integerDouble = floatingPointNumber;

        final int byteToInt = integerByte;
        final int shortToInt = integerShort;
        final int longToInt = (int) integerLong;
        final int doubleToInt = (int) integerDouble;

        final short byteToShort = integerByte;
        final short intToShort = (short) integer;
        final short longToShort = (short) integerLong;
        final short doubleToShort = (short) integerDouble;

        final byte shortToByte = integerShort;
        final byte intToByte = (byte) integer;
        final byte longToByte = (byte) integerLong;
        final byte doubleToByte = (byte)integerDouble;

        final long byteToLong = integerByte;
        final long shortToLong = integerShort;
        final long intToLong = integer;
        final long doubleToLong = (long) integerDouble;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isNaN(realNumber);
//        throw new NotImplementedException();
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isInfinite(realNumber);
//        throw new NotImplementedException();
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
