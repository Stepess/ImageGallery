package ua.training.model.entity;

import java.time.LocalDateTime;

public class RasterImage extends Image {
    public enum RasterFormat{
        JPEG, PNG, BMP

    }

    public RasterImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }

}
