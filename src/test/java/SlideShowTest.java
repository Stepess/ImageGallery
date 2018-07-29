import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.SlideShow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlideShowTest {
    private static List<Image> images;

    @BeforeClass
    public static void init(){
        images = new ArrayList<>();

        images.add(new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1"));
        images.add(new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2"));
        images.add(new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3"));
        images.add(new RasterImage("img4", "png", 3.12, LocalDateTime.now(), "tag1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenWriteUnsupportedFormatThrowException() {
        SlideShow slideShow = new SlideShow("testSlideShow", "txt", 0.0, LocalDateTime.now());
    }

    @Test
    public void WhenAddImagesThenTagsAddToSlideShow() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images.subList(0,3));

        Set<String> rightTags = new HashSet<>();
        for (Image img: images)
            rightTags.add(img.getTag());

        Assert.assertEquals(rightTags, slideShow.getTags());
    }

    @Test
    public void WhenAddImagesThenWeightSum() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images.subList(0,3));

        double totalWeight = images.get(0).getWeightInMb() + images.get(1).getWeightInMb() + images.get(2).getWeightInMb();

        Assert.assertEquals(totalWeight,
                slideShow.getWeightInMb(), 0.0);
    }

    @Test
    public void WhenRemoveImageThenWeightChange() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images.subList(0,3));

        slideShow.deleteFrame(slideShow.getFrames().size()-1);

        double totalWeight = images.get(0).getWeightInMb() + images.get(1).getWeightInMb();

        Assert.assertEquals(totalWeight,
                slideShow.getWeightInMb(), 0.0001);
    }


    @Test
    public void GivenTheOnlyImageHasTagWhenRemoveImageThenTagRemoveInSlideShow() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images.subList(0,3));

        slideShow.deleteFrame(0);

        Set<String> rightTags = new HashSet<>();
        rightTags.add(images.get(1).getTag());
        rightTags.add(images.get(2).getTag());

        Assert.assertEquals(rightTags, slideShow.getTags());
    }

    @Test
    public void GivenNotTheOnlyImageHasTagWhenRemoveImageThenTagRemainInSlideShow() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images);

        slideShow.deleteFrame(0);

        Set<String> rightTags = new HashSet<>();
        for (Image img: images)
            rightTags.add(img.getTag());

        Assert.assertEquals(rightTags, slideShow.getTags());
    }

    @Test
    public void WhenMergeSlideShowsThenWeightsSum() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0,
                LocalDateTime.now(), images.subList(0,3));
        SlideShow slideShow1 = new SlideShow("testSlideShow1", "avi", 0.0, LocalDateTime.now());

        slideShow1.addFrame(images.get(3));

        slideShow.mergeSlideShow(slideShow1);

        double totalWeight = images.get(0).getWeightInMb() + images.get(1).getWeightInMb()
                + images.get(2).getWeightInMb() + images.get(3).getWeightInMb();

        Assert.assertEquals(totalWeight, slideShow.getWeightInMb(), 0.0);
    }

}
