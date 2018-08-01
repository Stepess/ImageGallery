package ua.training.model.service.comparators;

import ua.training.model.entity.Image;

import java.util.Comparator;

public class ImageTagComparator implements Comparator<Image> {
    @Override
    public int compare(Image img1, Image img2) {
        return img1.getTag().compareTo(img2.getTag());
    }
}
