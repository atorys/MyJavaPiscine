package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Program {

    public static char BLACK;
    public static char WHITE;
    public static BufferedImage IMAGE;

    public static void main(String[] args) {
        if (!isValidArguments(args))
            return;

        char[][] array = new Converter().imageToArray(IMAGE, WHITE, BLACK);
        for (char[] pixels : array) {
            for (char aPixel : pixels) {
                System.out.print(aPixel);
            }
            System.out.println();
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Invalid arguments: WHITE BLACK IMAGE");
            return false;
        }
        try {
            IMAGE = ImageIO.read(Objects.requireNonNull(Program.class.getResource("/resources/image.bmp")));
        } catch (IOException e) {
            System.err.println("Invalid file");
            return false;
        }
        WHITE = args[0].toCharArray()[0];
        BLACK = args[1].toCharArray()[0];
        return true;
    }
}
