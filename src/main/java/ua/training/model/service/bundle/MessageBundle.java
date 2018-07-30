package ua.training.model.service.bundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundle {
    private ResourceBundle resourceBundle;

    public void setResourceBundle(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle(TextConstants.MESSAGES_BUNDLE_NAME,
                locale);
    }

    public String getMessageFromBundle(String key) {
        return resourceBundle.getString(key);
    }
}
