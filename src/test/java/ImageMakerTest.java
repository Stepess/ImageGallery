import org.junit.Assert;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.service.factory.ImageMaker;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.VectorImage;

import java.time.LocalDateTime;

public class ImageMakerTest {

    @Test
    public void WhenCreateImageWithPNGFormatThenCreateRasterImage() {
        ImageMaker imageMaker = ImageMaker.getImageMakerByFormat("png");

        Image image = imageMaker.makeImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");

        Assert.assertTrue(image instanceof RasterImage);
    }

    @Test
    public void WhenCreateImageWithAIFormatThenCreateVectorImage() {
        ImageMaker imageMaker = ImageMaker.getImageMakerByFormat("AI");

        Image image = imageMaker.makeImage("img1", "ai", 2.58, LocalDateTime.now(), "tag1");

        Assert.assertTrue(image instanceof VectorImage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenCreateImageWithUnsupportedFormatThenThrowException() {
        ImageMaker imageMaker = ImageMaker.getImageMakerByFormat("TXT");

        Image image = imageMaker.makeImage("img1", "ai", 2.58, LocalDateTime.now(), "tag1");
    }

}
