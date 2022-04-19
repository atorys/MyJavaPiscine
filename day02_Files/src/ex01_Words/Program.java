package ex01_Words;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Program {

    public static void main(String []args) {
        if (args.length < 2) {
            System.err.println("No input files");
            System.exit(-1);
        }
        try {
            FileReader file1 = new FileReader(Program.class.getResource(args[0]).getFile());
            FileReader file2 = new FileReader(Program.class.getResource(args[1]).getFile());

            char[]  text1 = new char[10000], text2 = new char[10000];

            text1[file1.read(text1, 0, 10000)] = '\0';
            text2[file2.read(text2, 0, 10000)] = '\0';

            ComparisonDictionary    comparison = new ComparisonDictionary();
            comparison.fill(text1, text2);
            System.out.printf("Similarity = %f\n", comparison.similarity());

            FileWriter dictionary = new FileWriter("dictionary.txt");

            TreeSet<String> keys = comparison.getUniqueSet();
            for (String key: keys) {
                dictionary.write(key);
                if (!key.equals(keys.last()))
                    dictionary.write(", ");
            }
            dictionary.flush();
            dictionary.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}