package ex00_FileSignatures;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Processer {

    private final SignatureQualifier    qualifier;
    private final OutputStream          out;
    private final Scanner               in;

    public Processer(InputStream infile, OutputStream outfile) throws IOException {
        this.qualifier = new SignatureQualifier(getReference(infile));
        in = new Scanner(System.in);
        out = outfile;
    }

    public char[] getReference(InputStream infile) throws IOException {
        if (infile == null)
            return null;
        int size = infile.available();
        char[] reference = new char[size];
        for (int i = 0; i < size; ++i)
            reference[i] = (char) infile.read();
        return reference;
    }

    public void start() throws IOException {
        for (;;) {
            String file = in.nextLine();
            if (file.equals("42"))
                break ;
            write(qualifier.verify(read(file)));
            System.out.println("PROCESSED");
        }
        in.close();
        out.close();
    }

    public char[] read(String file) throws IOException {
        InputStream infile = new FileInputStream(file);
        int size = infile.available();
        char[] reference = new char[size];
        for (int i = 0; i < size && i < 20; ++i)
            reference[i] = (char)infile.read();
        return reference;
    }
    public void write(String value) throws IOException {
        if (value == null)
            value = "UNDEFINED";
        char[] result = value.toCharArray();
        for (int i = 0; i < result.length; ++i)
            out.write(result[i]);
        out.write('\n');
    }
}
