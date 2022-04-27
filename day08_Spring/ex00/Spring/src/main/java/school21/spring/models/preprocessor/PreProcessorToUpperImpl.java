package school21.spring.models.preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String handleCase(String message) {
        return message.toUpperCase(Locale.ROOT);
    }
}
