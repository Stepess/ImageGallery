import org.junit.Assert;
import org.junit.Test;
import ua.training.model.entity.Image;
import ua.training.model.entity.ImageMaker;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.VectorImage;

import java.time.LocalDateTime;

public class ImageTest {

    @Test
    public void WhenCreateImageWithPNGFormatThenCreateRasterImage() {
        ImageMaker imageMaker = Image.getImageMakerByFormat("PNG");

        Image image = imageMaker.makeImage("img1", "png", 2.58, LocalDateTime.now(), "tag1");

        Assert.assertTrue(image instanceof RasterImage);
    }

    @Test
    public void WhenCreateImageWithAIFormatThenCreateVectorImage() {
        ImageMaker imageMaker = Image.getImageMakerByFormat("AI");

        Image image = imageMaker.makeImage("img1", "ai", 2.58, LocalDateTime.now(), "tag1");

        Assert.assertTrue(image instanceof VectorImage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenCreateImageWithUnsupportedFormatThenThrowException() {
        ImageMaker imageMaker = Image.getImageMakerByFormat("TXT");

        Image image = imageMaker.makeImage("img1", "ai", 2.58, LocalDateTime.now(), "tag1");
    }

}
