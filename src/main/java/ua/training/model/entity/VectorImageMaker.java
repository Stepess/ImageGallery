package ua.training.model.entity;

import java.time.LocalDateTime;

public class VectorImageMaker implements ImageMaker {
    @Override
    public Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag) {
        return new VectorImage(name, format, weightInMb, lastEdit, tag);
    }
}
