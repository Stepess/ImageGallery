package ua.training.model.service.factory;

import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;

import java.time.LocalDateTime;

public class RasterImageMaker extends ImageMaker {
    @Override
    public Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag) {
        return new RasterImage(name, format, weightInMb, lastEdit, tag);
    }
}
