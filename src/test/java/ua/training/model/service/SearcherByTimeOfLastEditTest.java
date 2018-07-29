package ua.training.model.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearcherByTimeOfLastEditTest {
    private static Service<Image> service;
    private static List<Image> data;
    private static List<Image> rightResult;
    private static List<Image> result;

    @BeforeClass
    public static void init(){
        data = new ArrayList<>();
        rightResult = new ArrayList<>();

        data.add(new RasterImage("img1", "png", 2.5, LocalDateTime.now(), "tag1"));
        data.add(new RasterImage("img2", "png", 12.5, LocalDateTime.now().minusYears(10), "tag2"));
        data.add(new RasterImage("img3", "png", 13.5, LocalDateTime.now().minusYears(1), "tag3"));
        data.add(new RasterImage("img4", "png", 0.5, LocalDateTime.now().minusYears(10), "tag4"));
        data.add(new RasterImage("img5", "png", 5.5, LocalDateTime.now(), "tag5"));
    }

    @Test
    public void searchTest(){
        service = new SearcherByTimeOfLastEdit(LocalDateTime.now().minusYears(2), LocalDateTime.now().plusYears(1));
        rightResult.add(data.get(0));
        rightResult.add(data.get(2));
        rightResult.add(data.get(4));

        result = service.search(data);

        Assert.assertEquals(rightResult, result);
    }

    @Test
    public void WhenNothingWasSearchedThenListIsEmpty(){
        service = new SearcherByTimeOfLastEdit(LocalDateTime.now().plusWeeks(1), LocalDateTime.now().plusWeeks(4));

        rightResult.clear();
        result = service.search(data);

        Assert.assertEquals(rightResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenRightBoundaryLessThenLeftBoundaryThenThrowException() {
        service = new SearcherByTimeOfLastEdit(LocalDateTime.now().plusYears(1), LocalDateTime.now().minusYears(1));
    }
}
