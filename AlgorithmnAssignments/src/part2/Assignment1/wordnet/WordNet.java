package part2.Assignment1.wordnet;


import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordNet {
    private Digraph G;
    private Bag<String>[] synsets;
    private String[] glosses;
    private List<String> wordNetNouns;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        argumentValidate(synsets, hypernyms);

        In syn = new In(synsets);
        In hyper = new In(hypernyms);
        if (syn.isEmpty()) {
            throw new IllegalArgumentException("the content in synsets file cannot be null!");
        } else if (hyper.isEmpty()) {
            throw new IllegalArgumentException("the content in hypernyms file cannot be null!");
        }

        String[] synlines = syn.readAll().split("\n");
        String[] hyperlines = hyper.readAllLines();

        G = new Digraph(synlines.length);
        for (String line : hyperlines) {
            String[] relation = line.split(",");
            G.addEdge(Integer.parseInt(relation[0]), Integer.parseInt(relation[1]));
            if (relation.length == 3) {
                G.addEdge(Integer.parseInt(relation[0]), Integer.parseInt(relation[2]));
            }
        }
        if (!isDAG(G)) {
            throw new IllegalArgumentException("The input to the constructor does not correspond to a rooted DAG");
        }

        this.synsets = new Bag[synlines.length];
        this.glosses = new String[synlines.length];

        wordNetNouns = new LinkedList<String>();
        for (String line : synlines) {
            String[] resolveLine = line.split(",");
            int index = Integer.parseInt(resolveLine[0]);
            this.glosses[index] = resolveLine[2];
            this.synsets[index] = new Bag<String>();
            String[] synonyms = resolveLine[1].trim().split(" ");
            for (String synonym : synonyms) {
                this.synsets[index].add(synonym);
                wordNetNouns.add(synonym);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return wordNetNouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return wordNetNouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        HashMap<String, Integer> nounIndex = this.getNounsIndex(nounA, nounB);
        if (nounIndex.size() != 2) {
            throw new IllegalArgumentException("The arguments aren't exist in wordNet!");
        }
        int a = nounIndex.get(nounA);
        int b = nounIndex.get(nounB);
        this.G.

    }


    // a synset (second field of synsets.text) that the common ancestor of nounA and
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

    }


    // assist methods
    private void argumentValidate(String check) {
        if (check == null) {
            throw new IllegalArgumentException("the  argument can't be null");
        }
    }

    private void argumentValidate(String check1, String check2) {
        if (check1 == null) {
            throw new IllegalArgumentException("the first argument can't be null");
        } else if (check2 == null) {
            throw new IllegalArgumentException("the first argument can't be null");
        } else if (check1 == null && check2 == null) {
            throw new IllegalArgumentException("the both arguments can't be null");
        }
    }

    boolean isDAG(Digraph G) {
        DirectedCycle directedCycle = new DirectedCycle(G);
        return !directedCycle.hasCycle();
    }

    HashMap<String, Integer> getNounsIndex(String nounA, String nounB) {
        HashMap<String, Integer> nounIndex = new HashMap<String, Integer>();
        for (int i = 0; i < synsets.length; i++) {
            for (String word : synsets[i]) {
                if (word == nounA) {
                    nounIndex.put(nounA, i);
                    if (nounIndex.size() == 2) {
                        return nounIndex;
                    }
                } else if (word == nounB) {
                    nounIndex.put(nounB, i);
                    if (nounIndex.size() == 2) {
                        return nounIndex;
                    }
                }
            }
        }
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }


}
