package edu.school21.printer.render;

import edu.school21.printer.processor.PreProcessor;

public class RendererErrImpl  implements Renderer{

    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String text) {
        System.err.println(preProcessor.process(text));
    }
}
