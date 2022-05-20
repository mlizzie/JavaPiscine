package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {

    private static final String IMAGE = "src/main/resources/it.bmp";
    private static String white,black;

    public static void convert(String arg1, String arg2) throws IOException {

        white = arg1;
        black = arg2;
        File file;
        BufferedImage source;
        file = new File(IMAGE);
        source = ImageIO.read(file);

        Ansi.BColor.valueOf(white);
        Ansi.BColor.valueOf(black);
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                ColoredPrinter cp = new ColoredPrinter();
                if (source.getRGB(y, x) == -1) {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }
}
