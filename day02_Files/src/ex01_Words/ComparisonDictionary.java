package ex01_Words;

import java.util.*;

public class ComparisonDictionary {

    private TreeMap<String, Integer>    dictionaryFirst;
    private TreeMap<String, Integer>    dictionarySecond;
    private TreeSet<String>             uniqueSet;

    public ComparisonDictionary() {
        dictionaryFirst = new TreeMap<>();
        dictionarySecond = new TreeMap<>();
        uniqueSet = new TreeSet<>();
    }

    public void fill(char[] source1, char[] source2) {
        fillDictionary(dictionaryFirst, source1);
        fillDictionary(dictionarySecond, source2);
        uniqueSet.addAll(dictionaryFirst.keySet());
        uniqueSet.addAll(dictionarySecond.keySet());
    }

    private void fillDictionary(TreeMap<String, Integer> dict, char[] source) {
        String text = Arrays.toString(source).replaceAll(", ", "");
        text = text.substring(1, text.indexOf('\0'));

        String[] buff1 = text.split(" ");

        for (String key : buff1) {
            if (dict.containsKey(key))
                dict.put(key, dict.get(key) + 1);
            else
                dict.put(key, 1);
        }
    }

    public float similarity() {
        float numerator = 0, denominator;
        int a = 0, b = 0;
        for (String key : uniqueSet) {
            if (dictionaryFirst.containsKey(key) && dictionarySecond.containsKey(key))
                numerator += dictionaryFirst.get(key) * dictionarySecond.get(key);
            if (dictionaryFirst.containsKey(key))
                a += dictionaryFirst.get(key) * dictionaryFirst.get(key);
            if (dictionarySecond.containsKey(key))
                b += dictionarySecond.get(key) * dictionarySecond.get(key);
        }
        denominator = (float)(Math.sqrt(a) * Math.sqrt(b));
        return ((int)(numerator/ denominator * 100) / 100.0f);
    }

    public TreeSet<String> getuniqueSet() {
        return uniqueSet;
    }
}
