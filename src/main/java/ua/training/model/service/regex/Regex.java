package ua.training.model.service.regex;

import java.util.regex.Pattern;

public interface Regex {
    String NAME_REGEX = "^[A-Za-z0-9_-]{3,20}$";
    String WEIGHT_REGEX = "^[0-9]{1,10}\\.?[0-9]{0,10}$";
    String TAG_REGEX = "^[A-Za-z0-9]{3,15}$";
}
