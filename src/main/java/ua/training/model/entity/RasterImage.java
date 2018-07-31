package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RasterImage extends Image {
    public enum RasterFormat{
        JPEG, PNG, BMP;

        public static boolean contains(String format){
            for (RasterFormat rasterFormat: RasterFormat.values())
                if (rasterFormat.toString().equals(format.toUpperCase()))
                    return true;
            return false;
        }

        public static List<String> getAllFormats(){
            List<String> result = new ArrayList<>();
            for (RasterFormat rasterFormat: RasterFormat.values())
                result.add(rasterFormat.toString().toLowerCase());
            return result;
        }
    }

    public RasterImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }

    @Override
    public String toString() {
        return "RasterImage" + super.toString();
    }
}
