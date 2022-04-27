package school21.spring.models.renderer;

import school21.spring.models.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
}
