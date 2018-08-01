package ua.training.model.service.search;

import ua.training.model.entity.Image;
import ua.training.model.service.database.ImageDAO;

import java.sql.SQLException;
import java.util.*;

public class SearcherByTag implements Service<Image> {
    private String tag;

    public SearcherByTag(String tag) {
        if (tag == null || tag.equals(""))
            throw new IllegalArgumentException("There are no tags!");
        this.tag = tag;
    }

    @Override
    public List<Image> search(List<Image> data) {
        List<Image> result = new ArrayList<>();

        for (Image img: data)
            if ( tag.equals(img.getTag()) )
                result.add(img);
        return result;
    }

    @Override
    public List<Image> searchInDB() throws SQLException {
        ImageDAO imageDAO = new ImageDAO();
        return imageDAO.getImagesByTag(tag);
    }
}