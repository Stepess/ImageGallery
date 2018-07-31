package ua.training.model.entity;

import ua.training.model.service.factory.ImageMaker;
import ua.training.model.service.factory.RasterImageMaker;
import ua.training.model.service.factory.VectorImageMaker;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Image extends File{
    protected String tag;

    public Image(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit);
        this.tag = tag;
    }


    @Override
    public void open() {
        this.toString();
    }

    @Override
    public void edit() {
        System.out.println("Changes added");
        setTimeOfLastEdit(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + "." + format + '\'' +
                ", tag='" + tag + '\'' +
                ", weightInMb=" + weightInMb +
                ", timeOfLastEdit=" + timeOfLastEdit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        if (!super.equals(o)) return false;
        Image image = (Image) o;
        return Objects.equals(tag, image.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tag);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

