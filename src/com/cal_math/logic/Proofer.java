package com.cal_math.logic;

import java.util.*;

public class Proofer {
    public static void main (String[] argv) {
        Atomic p = new Atomic("P");
        Atomic q = new Atomic("Q");
        ImplySentence is = new ImplySentence(p, q);

        ArrayList<Sentence> premises = new ArrayList<Sentence>();
        premises.add(p);
        premises.add(is);
        Sentence conclusion = q;

        Proof proof = new Proof(premises, conclusion, new String[]{"P", "Q"});
        proof.generateTruthTable();
    }

    private static void generateTruthTable(ArrayList<Sentence> premises, Sentence conclusion) {
        // Find out all symbols involved in both premises and conclusion
        Set<String> symbols = findSymbols(premises, conclusion);
        int n = symbols.size();
        String symbolList[] = new String[n];
        symbolList = symbols.toArray(symbolList);
        ArrayList<Map<String, Boolean>> truthCombinations = generateTruthCombinations(symbolList);
        for (Map<String, Boolean> truthCombination : truthCombinations)
            printTruthValues(truthCombination, premises, conclusion);
    }

    private static void printTruthValues(Map<String, Boolean> truthCombination, ArrayList<Sentence> premises, Sentence conclusion) {
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

    private static ArrayList<Map<String, Boolean>> generateTruthCombinations(String[] symbolList) {
        return generateTruthCombinationsHelper(0, symbolList) ;
    }

    private static ArrayList<Map<String, Boolean>> generateTruthCombinationsHelper(int index, String[] symbolList) {
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

    private static Set<String> findSymbols(ArrayList<Sentence> premises, Sentence conclusion) {
        Set<String> symbols = new HashSet<String>();
        symbols.add("P");
        symbols.add("Q");
        return symbols;
    }

}
