package edu.school21.printer.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "define output colors")
public class Arguments {
    @Parameter (names = {"--white"})
    private static String WHITE;
    @Parameter (names = {"--black"})
    private static String BLACK;

    String getWhite() {
        return WHITE;
    }
    String getBlack() {
        return BLACK;
    }
}
