package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        this.storage = matrixArray;
        if (storage == null) throw new IllegalArgumentException("Raw matrix is null");
        if (rows() == 0) throw new IllegalArgumentException("Raw matrix contains 0 row");
        if (Arrays.stream(storage).anyMatch(Objects::isNull)) throw new IllegalArgumentException("Raw matrix contains null row");
        if (Arrays.stream(storage).anyMatch(i -> i.length==0)) throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
        if (Arrays.stream(storage).anyMatch(i -> i.length != columns())) throw new IllegalArgumentException("Raw matrix is not rectangle");
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null) throw new IllegalArgumentException();
        if (left.columns() != right.rows()) throw new IllegalArgumentException();

        int[][] resultArray = new int[left.rows()][right.columns()];
        Matrix result = new Matrix(resultArray);

        for (int resultRowIndex = 0; resultRowIndex < left.rows(); resultRowIndex++) {
            for (int resultColumnIndex = 0; resultColumnIndex < right.columns(); resultColumnIndex++) {
                result.storage[resultRowIndex][resultColumnIndex] = result.multiplyRowAndColumn(left.getRow(resultRowIndex), right.getColumn(resultColumnIndex));
            }
        }
        return result;
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    public int multiplyRowAndColumn(int[] row, int[] column) {
        if (row.length != column.length) throw new IllegalArgumentException();
        return IntStream.range(0, row.length).map(i -> row[i] * column[i]).sum();
    }

    public int[] getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns()) throw new IllegalArgumentException();
        return IntStream.range(0,rows()).map(i -> storage[i][columnIndex]).toArray();
    }
    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
