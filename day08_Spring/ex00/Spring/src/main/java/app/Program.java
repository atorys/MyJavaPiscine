package app;

import models.preprocessor.PreProcessor;
import models.preprocessor.PreProcessorToUpperImpl;
import models.printer.Printer;
import models.printer.PrinterWithPrefixImpl;
import models.renderer.Renderer;
import models.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

    public static void mainStandardWay() {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix ("Prefix");
        printer.print ("Hello!") ;
    }

    public static void mainWithSpring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefixImpl", Printer.class);
        printer.print("Hello!") ;
    }


    public static void main(String[] args) {
        mainStandardWay();
        mainWithSpring();
    }
}
