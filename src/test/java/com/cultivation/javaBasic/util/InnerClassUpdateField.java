package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void add() {
        class InnerClass{

            final static private int yeah = 3;

            public InnerClass(int year) {
            }

            public void add(){
            }

        }
        new InnerClass(2).add();
    }

    public class InnerClass {
        private int year;
        final static private int yeah = 3;
        public InnerClass(int year) {
            this.year = year;
        }

        public void add() {
            InnerClassUpdateField.this.year += this.year;
        }

    }
}
