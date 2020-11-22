package com.cal_math.logic;

import java.util.Map;

public class ImplySentence implements Sentence {
    Sentence antecedent;
    Sentence consequence;

    public ImplySentence (Sentence antecedent, Sentence consequence) {
        this.antecedent = antecedent;
        this.consequence = consequence;
    }

    @Override
    public boolean eval(Map<String, Boolean> truthCombination) {
        boolean antBool = antecedent.eval(truthCombination);
        boolean consBool = consequence.eval(truthCombination);

        if (antBool) {
            return consBool;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return antecedent.toString() + " -> " + consequence.toString();
    }
}
