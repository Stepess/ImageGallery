package ua.training.model.entity;

import java.time.LocalDateTime;

public abstract class File {
    protected String name;
    protected String format;
    protected double weightInMb;
    protected LocalDateTime timeOfLastEdit;

    public File(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit) {
        this.name = name;
        this.format = format;
        this.weightInMb = weightInMb;
        this.timeOfLastEdit = timeOfLastEdit;
    }

    public abstract void open();
    public abstract void edit();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getWeightInMb() {
        return weightInMb;
    }

    public void setWeightInMb(double weightInMb) {
        this.weightInMb = weightInMb;
    }

    public LocalDateTime getTimeOfLastEdit() {
        return timeOfLastEdit;
    }

    public void setTimeOfLastEdit(LocalDateTime timeOfLastEdit) {
        this.timeOfLastEdit = timeOfLastEdit;
    }
}
