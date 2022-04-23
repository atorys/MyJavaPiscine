package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.api.Ansi;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Converter {

    public char[][] imageToArray(BufferedImage image, char white, char black) {
        int width = image.getWidth();
        int height = image.getHeight();

        char[][] array = new char[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                array[i][j] = image.getRGB(j, i) == Color.BLACK.getRGB() ? black : white;
            }
        }
        return array;
    }
    public Ansi.BColor[][] imageToColoredArray(BufferedImage image, Ansi.BColor white, Ansi.BColor black) {
        int width = image.getWidth();
        int height = image.getHeight();

        Ansi.BColor[][] array = new Ansi.BColor[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                array[i][j] = image.getRGB(j, i) == Color.BLACK.getRGB() ? black : white;
            }
        }
        return array;
    }
}
