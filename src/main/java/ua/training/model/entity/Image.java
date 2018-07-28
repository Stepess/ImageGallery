package ua.training.model.entity;

import java.time.LocalDateTime;

public abstract class Image extends File{
    protected String tag;

    public Image(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, String tag) {
        super(name, format, weightInMb, timeOfLastEdit);
        this.tag = tag;
    }

    public static ImageMaker getImageMakerByFormat(String format) {
        if (RasterImage.RasterFormat.contains(format))
            return new RasterImageMaker();
        else if (VectorImage.VectorFormat.contains(format))
            return new VectorImageMaker();
        else
            throw new IllegalArgumentException("Unfortunately, such format isn't supported.");
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
        return "RasterImage{" +
                "name='" + name + "." + format + '\'' +
                ", tag='" + tag + '\'' +
                ", weightInMb=" + weightInMb +
                ", timeOfLastEdit=" + timeOfLastEdit +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

