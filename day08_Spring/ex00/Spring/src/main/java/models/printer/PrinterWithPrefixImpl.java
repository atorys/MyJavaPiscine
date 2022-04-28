package models.printer;

import models.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;
    private String prefix = null;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void print(String message) {
        renderer.print(prefix + message);
    }
}
