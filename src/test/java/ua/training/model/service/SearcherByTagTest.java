package ua.training.model.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.service.SearcherByTag;
import ua.training.model.service.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearcherByTagTest {
    private static Service<Image> service;
    private static List<Image> data;
    private static List<Image> rightResult;
    private static List<Image> result;

    @BeforeClass
    public static void init(){
        data = new ArrayList<>();
        rightResult = new ArrayList<>();

        data.add(new RasterImage("img1", "png", 2.5, LocalDateTime.now(), "tag1"));
        data.add(new RasterImage("img2", "png", 12.5, LocalDateTime.now(), "tag2"));
        data.add(new RasterImage("img3", "png", 13.5, LocalDateTime.now(), "tag3"));
        data.add(new RasterImage("img4", "png", 0.5, LocalDateTime.now(), "tag4"));
        data.add(new RasterImage("img5", "png", 5.5, LocalDateTime.now(), "tag5"));
    }

    @Test
    public void searchTest(){
        service = new SearcherByTag("tag1", "tag2", "tag4");
        rightResult.add(data.get(0));
        rightResult.add(data.get(1));
        rightResult.add(data.get(3));

        result = service.search(data);

        Assert.assertEquals(rightResult, result);
    }

    @Test
    public void WhenNothingWasSearchedThenListIsEmpty(){
        service = new SearcherByTag("tag13");

        rightResult.clear();
        result = service.search(data);

        Assert.assertEquals(rightResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenThereAreNotTagsThenThrowException() {
        service = new SearcherByTag();
    }
}
