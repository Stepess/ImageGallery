import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.SlideShow;
import java.util.HashSet;
import java.util.Set;

public class SlideShowTest {

    @Test
    public void WhenAddImagesThenTagsAddToSlideShow(){
        SlideShow slideShow = new SlideShow("testSlideShow", "avi", 0.0, LocalDateTime.now());

        Image img1 = new RasterImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");
        Image img2 = new RasterImage("img2", "jpeg", 2.12, LocalDateTime.now(), "tag2");
        Image img3 = new RasterImage("img3", "bmp", 3.97, LocalDateTime.now(), "tag3");

        slideShow.addFrame(img1);
        slideShow.addFrame(img2);
        slideShow.addFrame(img3);

        Set<String> rightTegsSet = new HashSet<>();
        rightTegsSet.add(img1.getTag());
        rightTegsSet.add(img2.getTag());
        rightTegsSet.add(img3.getTag());

        Assert.assertEquals(rightTegsSet, slideShow.getTags());
    }
}
