package models.printer;

import models.renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private final Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void print(LocalDateTime message) {
        renderer.print(message.toString());
    }
}
