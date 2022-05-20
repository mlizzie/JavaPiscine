package edu.school21.printer.printer;

import edu.school21.printer.render.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    public PrinterWithDateTimeImpl(Renderer renderer) {
    }

    public void print(String text) {
        System.out.println(text);
    }
}
