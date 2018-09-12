package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
        class YearIncrement {


            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    year++;
                }
            };
        }
        new YearIncrement().runnable.run();
    }
}