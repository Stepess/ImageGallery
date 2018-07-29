package ua.training.model.service.factory;

import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.VectorImage;

import java.time.LocalDateTime;

public abstract class ImageMaker {
    public abstract Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag);

    public static ImageMaker getImageMakerByFormat(String format) {
        if (RasterImage.RasterFormat.contains(format))
            return new RasterImageMaker();
        else if (VectorImage.VectorFormat.contains(format))
            return new VectorImageMaker();
        else
            throw new IllegalArgumentException("Unfortunately, such format isn't supported.");
    }
}
