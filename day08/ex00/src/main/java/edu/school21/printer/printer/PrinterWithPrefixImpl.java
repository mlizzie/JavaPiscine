package edu.school21.printer.printer;

import edu.school21.printer.render.Renderer;

public class PrinterWithPrefixImpl {
    private String prefix;
    private Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void print(String text) {
        renderer.print(this.prefix + " " + text);
    }
}
