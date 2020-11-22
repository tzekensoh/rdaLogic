package com.cal_math.logic;

import java.util.*;

public class Proof {
    public static void main (String[] argv) {
        Atomic p = new Atomic("P");
        Atomic q = new Atomic("Q");
        ImplySentence is = new ImplySentence(p, q);

        ArrayList<Sentence> premises = new ArrayList<Sentence>();
        premises.add(p);
        premises.add(is);
        Sentence conclusion = q;

        generateTruthTable(premises, conclusion);
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
        ArrayList<Map<String, Boolean>> truthCombinations = new ArrayList<Map<String, Boolean>>();
        Map<String, Boolean> hardCodedMap1 = new HashMap<String, Boolean>();
        hardCodedMap1.put("P", new Boolean(true));
        hardCodedMap1.put("Q", new Boolean(true));
        Map<String, Boolean> hardCodedMap2 = new HashMap<String, Boolean>();
        hardCodedMap2.put("P", new Boolean(true));
        hardCodedMap2.put("Q", new Boolean(false));
        truthCombinations.add(hardCodedMap1);
        truthCombinations.add(hardCodedMap2);
        return truthCombinations;
    }

    private static Set<String> findSymbols(ArrayList<Sentence> premises, Sentence conclusion) {
        Set<String> symbols = new HashSet<String>();
        symbols.add("P");
        symbols.add("Q");
        return symbols;
    }

}
