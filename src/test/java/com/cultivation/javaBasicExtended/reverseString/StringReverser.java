package com.cultivation.javaBasicExtended.reverseString;

import java.util.Arrays;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start

        if (input == null) throw new IllegalArgumentException();

        String[] originalStringArray = input.equals("") ? new String[0] : input.split(" ");

        int length = originalStringArray.length;
        String[] resultArray = new String[length];

        for (int originalIndex = 0, resultIndex = length-1; originalIndex < length; originalIndex++) {
            resultArray[resultIndex] = originalStringArray[originalIndex];
            resultIndex --;
        }
        return resultArray;
        // --end-->
    }
}
