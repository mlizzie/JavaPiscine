package edu.school21.printer.processor;

public class PreProcessorToLowerImpl implements PreProcessor{

    @Override
    public String process(String text) {
        return text.toLowerCase();
    }
}
