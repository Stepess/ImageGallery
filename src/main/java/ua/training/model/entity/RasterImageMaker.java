package ua.training.model.entity;

import java.time.LocalDateTime;

public class RasterImageMaker implements ImageMaker {
    @Override
    public Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag) {
        return new RasterImage(name, format, weightInMb, lastEdit, tag);
    }
}
