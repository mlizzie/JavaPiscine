package edu.school21.printer.processor;

public class PreProcessorToUpperImpl implements PreProcessor{

    @Override
    public String process(String text) {
        return text.toUpperCase();
    }
}
