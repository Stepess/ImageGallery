package ua.training.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;

import java.time.LocalDateTime;
import java.util.*;

public class AllComparatorsTest {
    private static List<Image> images;
    private static List<Image> rightOrder;

    @Before
    public void init() {
        images = new ArrayList<>();

        LocalDateTime ldt = LocalDateTime.now();

        images.add(new RasterImage("foto1", "jpeg", 25.2,ldt,"#sea"));
        images.add(new RasterImage("foto2", "jpeg", 21.3,ldt.minusDays(20),"#holidays"));
        images.add(new RasterImage("foto3", "jpeg", 7.9,ldt.minusDays(20).minusHours(1),"#happy"));
        images.add(new RasterImage("foto4", "jpeg",33.3, ldt.minusYears(23), "#meme"));
    }

    @Test
    public void SortByTagTest() {
        rightOrder = new ArrayList<>();

        rightOrder.add(images.get(2));
        rightOrder.add(images.get(1));
        rightOrder.add(images.get(3));
        rightOrder.add(images.get(0));

        Collections.sort(images, new ImageTagComparator());

        Assert.assertEquals(rightOrder, images);
    }

    @Test
    public void SortByWeightTest() {
        rightOrder = new ArrayList<>();

        rightOrder.add(images.get(2));
        rightOrder.add(images.get(1));
        rightOrder.add(images.get(0));
        rightOrder.add(images.get(3));

        Collections.sort(images, new ImageWeightComparator());

        Assert.assertEquals(rightOrder, images);
    }

    @Test
    public void SortByTimeOfLastEditTest() {
        rightOrder = new ArrayList<>();

        rightOrder.add(images.get(3));
        rightOrder.add(images.get(2));
        rightOrder.add(images.get(1));
        rightOrder.add(images.get(0));

        Collections.sort(images, new ImageTimeOfLastEditComparator());

        Assert.assertEquals(rightOrder, images);
    }
}
