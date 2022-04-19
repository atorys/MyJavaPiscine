package ex00_FileSignatures;

import java.io.*;

public class Program {

    public static String    pathToSignatures = "/Users/atory/IdeaProjects/myJavaPiscine/src/Day02/ex00_FileSignatures/signatures.txt";

    public static void main(String []args) throws IOException {
        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(pathToSignatures);
            output = new FileOutputStream("result.txt");

            Processer read_write_process = new Processer(input, output);
            read_write_process.start();

        }
        catch (IOException e) {
            System.out.println("Error occurred! Check that 'signatures.txt' file exist!");
        }
        finally {
            if (input != null)
                input.close();

            if (output != null)
                output.close();
        }
    }
}
