package ua.training.model.entity;

import java.time.LocalDateTime;

public class VectorImage extends Image {
    public enum VectorFormat{
        AI, CMX, CDR, SWG;

        public static boolean contains(String format){
            for (VectorFormat vectorFormat: VectorFormat.values())
                if (vectorFormat.toString().equals(format.toUpperCase()))
                    return true;
            return false;
        }
    }

    public VectorImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }

    @Override
    public String toString() {
        return "VectorImage" + super.toString();
    }
}
