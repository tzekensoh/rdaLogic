package com.cal_math.logic;

import java.util.Map;

public interface Sentence {
    boolean eval(Map<String, Boolean> truthCombination);
}
