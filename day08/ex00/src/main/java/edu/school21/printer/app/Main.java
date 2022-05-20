package edu.school21.printer.app;

import edu.school21.printer.printer.Printer;
import edu.school21.printer.printer.PrinterWithPrefixImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        PrinterWithPrefixImpl printer = context.getBean("printer", PrinterWithPrefixImpl.class);
        printer.print ("Hello!") ;
    }
}