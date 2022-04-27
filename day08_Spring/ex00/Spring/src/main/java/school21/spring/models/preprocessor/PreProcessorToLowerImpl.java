package school21.spring.models.preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String handleCase(String message) {
        return message.toLowerCase(Locale.ROOT);
    }
}
