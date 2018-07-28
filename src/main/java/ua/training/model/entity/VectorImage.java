package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class VectorImage extends Image {
    public enum VectorFormat{
        AI, CMX, CDR, SWG;

        public static boolean contains(String format){
            return Arrays.toString(VectorFormat.values()).contains(format.toUpperCase());
        }
    }

    public VectorImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }
}
