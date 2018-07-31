package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VectorImage extends Image {
    public enum VectorFormat{
        AI, CMX, CDR, SWG;

        public static boolean contains(String format){
            for (VectorFormat vectorFormat: VectorFormat.values())
                if (vectorFormat.toString().equals(format.toUpperCase()))
                    return true;
            return false;
        }

        public static List<String> getAllFormats(){
            List<String> result = new ArrayList<>();
            for (VectorFormat vectorFormat: VectorFormat.values())
                result.add(vectorFormat.toString().toLowerCase());
            return result;
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
