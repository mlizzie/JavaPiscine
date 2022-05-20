package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //if (1 != args.length) {
          // System.err.println("\"Error: Read README.txt");
          // System.exit(-1);
        //}
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("./resources/it.bmp"));
        } catch (IOException e) {
            System.err.println("Error: path incorrect");
            System.exit(-1);
        }
        Logic.convert(img);


    }
}