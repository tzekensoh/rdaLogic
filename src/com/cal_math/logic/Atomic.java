package com.cal_math.logic;

import java.util.Map;

public class Atomic implements Sentence {
    String symbol;

    public Atomic (String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean eval(Map<String, Boolean> truthCombination) {
        return truthCombination.get(symbol).booleanValue();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
