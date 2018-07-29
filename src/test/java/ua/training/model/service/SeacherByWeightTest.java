package ua.training.model.service;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeacherByWeightTest {
    private static Service<Image> service;
    private static List<Image> data;

    @BeforeClass
    public static void init(){
        service = new SearcherByWeight();
        data = new ArrayList<>();

        data.add(new RasterImage("img1", "png", 2.5, LocalDateTime.now(), "tag1"));
        data.add(new RasterImage("img2", "png", 12.5, LocalDateTime.now(), "tag2"));
        data.add(new RasterImage("img3", "png", 13.5, LocalDateTime.now(), "tag3"));
        data.add(new RasterImage("img4", "png", 0.5, LocalDateTime.now(), "tag4"));
        data.add(new RasterImage("img5", "png", 5.5, LocalDateTime.now(), "tag5"));
    }

    @Test
    public void WhenNothingWasSerchedThenListIsEmpty(){

    }
}
