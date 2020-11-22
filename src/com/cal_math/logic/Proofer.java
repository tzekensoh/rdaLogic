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
}
