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
            int size = 10000000;
            FileReader file1 = new FileReader(Program.class.getResource(args[0]).getFile());
            FileReader file2 = new FileReader(Program.class.getResource(args[1]).getFile());

            char[]  text1 = new char[size], text2 = new char[size];

            if (file1.ready())
                text1[file1.read(text1, 0, size)] = '\0';
            if (file2.ready())
                text2[file2.read(text2, 0, size)] = '\0';

            ComparisonDictionary    comparison = new ComparisonDictionary();
            comparison.fill(text1, text2);
            System.out.printf("Similarity = %.2f\n", comparison.similarity());

            FileWriter dictionary = new FileWriter("dictionary.txt");

            TreeSet<String> keys = comparison.getuniqueSet();
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