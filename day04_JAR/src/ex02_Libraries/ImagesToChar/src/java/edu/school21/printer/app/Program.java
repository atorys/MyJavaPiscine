package edu.school21.printer.app;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import edu.school21.printer.logic.Converter;
import com.beust.jcommander.JCommander;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Program {

    public static String BLACK;
    public static String WHITE;
    public static BufferedImage IMAGE;

    public static void main(String[] args) {
        if (!isValidArguments(args))
            return;

        Arguments arg = new Arguments();
        JCommander jc = JCommander.newBuilder().addObject(arg).build();
        ColoredPrinter printer = new ColoredPrinter.Builder(1, false).build();
        jc.parse(args);
        WHITE = arg.getWhite();
        BLACK = arg.getBlack();

        Ansi.BColor[][] array = new Converter().imageToColoredArray(IMAGE,
                                                                    Ansi.BColor.valueOf(arg.getWhite()),
                                                                    Ansi.BColor.valueOf(arg.getBlack()));
        for (Ansi.BColor[] colorPixels : array) {
            for (Ansi.BColor aColorPixel : colorPixels) {
                printer.setBackgroundColor(aColorPixel);
                printer.print(" ");
            }
            System.out.println();
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--white") || !args[1].startsWith("--black")) {
            System.err.println("Invalid arguments: --white=? --black=?");
            return false;
        }
        try {
            IMAGE = ImageIO.read(Objects.requireNonNull(Program.class.getResource("/resources/image.bmp")));
        } catch (IOException e) {
            System.err.println("Invalid file");
            return false;
        }
        return true;
    }
}
