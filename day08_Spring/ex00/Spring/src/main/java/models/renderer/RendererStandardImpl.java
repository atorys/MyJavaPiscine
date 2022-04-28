package models.renderer;

import models.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    public void print(String message) {
        System.err.println(preProcessor.handleCase(message));
    }
}
