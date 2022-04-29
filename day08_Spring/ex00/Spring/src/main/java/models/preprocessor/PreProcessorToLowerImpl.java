package models.preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {

    public PreProcessorToLowerImpl() {}
    @Override
    public String handleCase(String message) {
        return message.toLowerCase(Locale.ROOT);
    }
}
