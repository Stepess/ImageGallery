package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        File file = (File) o;
        return Double.compare(file.weightInMb, weightInMb) == 0 &&
                Objects.equals(name, file.name) &&
                Objects.equals(format, file.format) &&
                Objects.equals(timeOfLastEdit, file.timeOfLastEdit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, format, weightInMb, timeOfLastEdit);
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
