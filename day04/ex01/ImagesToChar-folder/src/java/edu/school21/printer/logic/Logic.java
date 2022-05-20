package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {

    private static final int WHITE = -1;
    private static char whiteSymb = '.';
    private static char blackSymb = '0';

    public static void convert(BufferedImage img){
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (img.getRGB(j, i) == WHITE) {
                    System.out.print(whiteSymb);
                } else {
                    System.out.print(blackSymb);
                }
            }
            System.out.println();
        }
    }
}

