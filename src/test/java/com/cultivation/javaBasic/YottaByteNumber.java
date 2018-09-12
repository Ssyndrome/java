package com.cultivation.javaBasic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YottaByteNumber implements Iterable<Double>{
    private double maximum;

    public YottaByteNumber(double maximum) {
        this.maximum = maximum;
    }

    @Override
    public Iterator<Double> iterator() {
        return new YottaByteIterator(maximum);
    }

    public List<Double> toList () {
        List<Double> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class YottaByteIterator implements Iterator<Double>{
    private double index;
    private double end;

    public YottaByteIterator(double end) {
        this.index = 1;
        this.end = end;
    }

    @Override
    public Double next() {
        return Math.pow(index++, 10);
    }

    @Override
    public boolean hasNext() {
        return Math.pow(index, 10) <= end;
    }
}
