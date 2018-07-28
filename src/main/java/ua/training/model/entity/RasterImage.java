package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class RasterImage extends Image {
    public enum RasterFormat{
        JPEG, PNG, BMP;

        public static boolean contains(String format){
            return Arrays.toString(RasterFormat.values()).contains(format.toUpperCase());
        }
    }

    public RasterImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }

}
