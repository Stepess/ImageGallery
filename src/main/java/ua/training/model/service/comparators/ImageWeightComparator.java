package ua.training.model.service.comparators;

import ua.training.model.entity.Image;

import java.util.Comparator;

public class ImageWeightComparator implements Comparator<Image> {
    @Override
    public int compare(Image img1, Image img2) {
        Double weight1 = img1.getWeightInMb();
        Double weight2 = img2.getWeightInMb();
        return weight1.compareTo(weight2);
    }
}
