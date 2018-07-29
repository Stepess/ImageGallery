package ua.training.model.service.factory;

import ua.training.model.entity.Image;
import ua.training.model.entity.VectorImage;

import java.time.LocalDateTime;

public class VectorImageMaker extends ImageMaker {
    @Override
    public Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag) {
        return new VectorImage(name, format, weightInMb, lastEdit, tag);
    }
}
