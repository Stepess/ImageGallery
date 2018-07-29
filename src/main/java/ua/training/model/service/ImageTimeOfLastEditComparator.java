package ua.training.model.service;

import ua.training.model.entity.Image;

import java.util.Comparator;

public class ImageTimeOfLastEditComparator implements Comparator<Image> {
    @Override
    public int compare(Image img1, Image img2) {
        return img1.getTimeOfLastEdit().compareTo(img2.getTimeOfLastEdit());
    }
}
