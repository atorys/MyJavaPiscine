package models.renderer;

import models.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    public void print(String message) {
        System.err.println(preProcessor.handleCase(message));
    }
}
