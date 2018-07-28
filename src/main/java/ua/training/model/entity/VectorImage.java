package ua.training.model.entity;

import java.time.LocalDateTime;

public class VectorImage extends Image {
    public enum VectorFormat{
        AI, CMX, CDR, SWG
    }

    public VectorImage(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit, tag);
    }
}
