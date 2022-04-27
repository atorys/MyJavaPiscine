package school21.spring.models.printer;

import school21.spring.models.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;
    private String prefix = null;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {

    }
}
