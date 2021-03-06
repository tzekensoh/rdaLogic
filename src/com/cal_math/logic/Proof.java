package com.cal_math.logic;

import java.util.*;

public class Proof {

    ArrayList<Sentence> premises;
    Sentence conclusion;
    String symbolList[];

    public Proof(ArrayList<Sentence> premises, Sentence conclusion, String symbolList[]) {
        this.premises = premises;
        this.conclusion = conclusion;
        this.symbolList = symbolList;
    }

    public void generateTruthTable() {
        ArrayList<Map<String, Boolean>> truthCombinations = generateTruthCombinations(symbolList);
        for (Map<String, Boolean> truthCombination : truthCombinations)
            printTruthValues(truthCombination);
    }

    private void printTruthValues(Map<String, Boolean> truthCombination) {
        // Print truth combination
        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String,Boolean> entry : truthCombination.entrySet())
            System.out.println(entry.getKey() +
                    " = " + entry.getValue());

        for (Sentence sentence : premises)
            System.out.println(sentence.toString() + " = " + sentence.eval(truthCombination));

        System.out.println(conclusion.toString() + " = " + conclusion.eval(truthCombination));
        System.out.println("------------------------------------------------");
    }

    private ArrayList<Map<String, Boolean>> generateTruthCombinations(String[] symbolList) {
        return generateTruthCombinationsHelper(0, symbolList) ;
    }

    private ArrayList<Map<String, Boolean>> generateTruthCombinationsHelper(int index, String[] symbolList) {
        // base case: reach last element
        if (index == symbolList.length - 1) {
            // Add two maps to a new ArrayList with the last symbol T and F
            ArrayList<Map<String, Boolean>> truthCombinations = new ArrayList<Map<String, Boolean>>();
            Map<String, Boolean> baseMap1 = new HashMap<String, Boolean>();
            baseMap1.put(symbolList[index], new Boolean(true));
            Map<String, Boolean> baseMap2 = new HashMap<String, Boolean>();
            baseMap2.put(symbolList[index], new Boolean(false));
            truthCombinations.add(baseMap1);
            truthCombinations.add(baseMap2);
            return truthCombinations;
        } else {
            // call with index + 1
            // duplicate the returned maps
            // Add to these maps with the current symbol T and F
            ArrayList<Map<String, Boolean>> truthCombinations = generateTruthCombinationsHelper(index + 1, symbolList);
            ArrayList<Map<String, Boolean>> clonedCombinations = new ArrayList<Map<String, Boolean>>();
            for (Map<String, Boolean> truthCombination : truthCombinations) {
                truthCombination.put(symbolList[index], new Boolean(true));

                Map<String, Boolean> clonedTruthCombination = new HashMap<String, Boolean>(truthCombination);
                clonedTruthCombination.put(symbolList[index], new Boolean(false));
                clonedCombinations.add(clonedTruthCombination);
            }
            truthCombinations.addAll(clonedCombinations);
            return truthCombinations;
        }
    }

}
