package ex01_Words;

import java.util.*;

public class ComparisonDictionary {

    private TreeMap<String, Integer>    Dictionary1;
    private TreeMap<String, Integer>    Dictionary2;
    private TreeSet<String>             UniqueSet;

    public ComparisonDictionary() {
        Dictionary1 = new TreeMap<>();
        Dictionary2 = new TreeMap<>();
        UniqueSet = new TreeSet<>();
    }

    public void fill(char[] source1, char[] source2) {
        fillDictionary(Dictionary1, source1);
        fillDictionary(Dictionary2, source2);
        UniqueSet.addAll(Dictionary1.keySet());
        UniqueSet.addAll(Dictionary2.keySet());
    }

    private void fillDictionary(TreeMap<String, Integer> dict, char[] source) {
        String text = Arrays.toString(source).replaceAll(", ", "");
        text = text.substring(1, text.indexOf('\0'));

        System.out.println(text);
        String[] buff1 = text.split(" ");

        for (String key : buff1) {
            if (dict.containsKey(key))
                dict.put(key, dict.get(key) + 1);
            else
                dict.put(key, 1);
        }
    }

    public double similarity() {
        double numerator = 0;
        double denominator;
        int a = 0, b = 0;
        for (String key : UniqueSet) {
            if (Dictionary1.containsKey(key) && Dictionary2.containsKey(key))
                numerator += Dictionary1.get(key) * Dictionary2.get(key);
            if (Dictionary1.containsKey(key))
                a += Dictionary1.get(key) * Dictionary1.get(key);
            if (Dictionary2.containsKey(key))
                b += Dictionary2.get(key) * Dictionary2.get(key);
        }
        denominator = Math.sqrt(a) * Math.sqrt(b);
        return (numerator / denominator);
    }

    public TreeSet<String> getUniqueSet() {
        return UniqueSet;
    }
}
