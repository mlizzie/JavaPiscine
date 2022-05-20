package edu.school21.printer.render;

import edu.school21.printer.processor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    PreProcessor preProcessor;

    RendererStandardImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String text) {
        System.out.println(preProcessor.process(text));
    }
}
