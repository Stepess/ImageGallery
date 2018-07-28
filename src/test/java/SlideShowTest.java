import org.junit.Assert;
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

    @Test
    public void WhenAddImagesThenTagsAddToSlideShow() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now());

        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        slideShow.addFrame(img1);
        slideShow.addFrame(img2);
        slideShow.addFrame(img3);

        Set<String> rightTagsSet = new HashSet<>();
        rightTagsSet.add(img1.getTag());
        rightTagsSet.add(img2.getTag());
        rightTagsSet.add(img3.getTag());

        Assert.assertEquals(rightTagsSet, slideShow.getTags());
    }

    @Test
    public void WhenAddImagesThenWeightSum() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now());

        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        slideShow.addFrame(img1);
        slideShow.addFrame(img2);
        slideShow.addFrame(img3);

        double totalWeight = img1.getWeightInMb() + img2.getWeightInMb() + img3.getWeightInMb();

        Assert.assertEquals(totalWeight,
                slideShow.getWeightInMb(), 0.0);
    }

    @Test
    public void WhenRemoveImageThenWeightChange() {
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now());

        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        slideShow.addFrame(img1);
        slideShow.addFrame(img2);
        slideShow.addFrame(img3);
        slideShow.deleteFrame(slideShow.getFrames().size()-1);

        double totalWeight = img1.getWeightInMb() + img2.getWeightInMb();

        Assert.assertEquals(totalWeight,
                slideShow.getWeightInMb(), 0.0001);
    }


    @Test
    public void GivenTheOnlyImageHasTagWhenRemoveImageThenTagRemoveFromSlideShow() {
        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        List<Image> imageList = new ArrayList<>();

        imageList.add(img1);
        imageList.add(img2);
        imageList.add(img3);

        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now(), imageList);

        slideShow.deleteFrame(0);

        Set<String> rightTags = new HashSet<>();

        rightTags.add(img2.getTag());
        rightTags.add(img3.getTag());

        Assert.assertEquals(rightTags, slideShow.getTags());
    }

    @Test
    public void GivenNotTheOnlyImageHasTagWhenRemoveImageThenTagRemainFromSlideShow() {
        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img4 = new RasterImage("img4", "png", 3.12, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        List<Image> imageList = new ArrayList<>();

        imageList.add(img1);
        imageList.add(img2);
        imageList.add(img3);
        imageList.add(img4);

        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now(), imageList);

        slideShow.deleteFrame(0);

        Set<String> rightTags = new HashSet<>();

        rightTags.add(img1.getTag());
        rightTags.add(img2.getTag());
        rightTags.add(img3.getTag());

        Assert.assertEquals(rightTags, slideShow.getTags());
    }

    @Test
    public void WhenMergeSlideShowsThenWeightsSum() {
        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img4 = new RasterImage("img4", "png", 3.12, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        List<Image> imageList = new ArrayList<>();

        imageList.add(img1);
        imageList.add(img2);
        imageList.add(img3);

        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now(), imageList);
        SlideShow slideShow1 = new SlideShow("testSlideShow1", "avi", 0.0, LocalDateTime.now());

        slideShow1.addFrame(img4);

        slideShow.mergeSlideShow(slideShow1);

        double totalWeight = img1.getWeightInMb() + img2.getWeightInMb() + img3.getWeightInMb() + img4.getWeightInMb();

        Assert.assertEquals(totalWeight, slideShow.getWeightInMb(), 0.0);
    }

}
