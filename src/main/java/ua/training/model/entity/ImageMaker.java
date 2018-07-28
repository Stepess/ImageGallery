package ua.training.model.entity;

import java.time.LocalDateTime;

public interface ImageMaker {
    Image makeImage(String name, String format, double weightInMb, LocalDateTime lastEdit, String tag);
}
