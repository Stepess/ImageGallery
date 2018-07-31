package ua.training.model.service;

import ua.training.model.entity.Image;
import ua.training.model.service.database.ImageDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearcherByWeight implements Service<Image> {
    private double leftBoundary;
    private double rightBoundary;

    public SearcherByWeight(double leftBoundary, double rightBoundary) {
        if (rightBoundary - leftBoundary < 0 )
            throw new IllegalArgumentException("Wrong boundaries!");
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }

    @Override
    public List<Image> search(List<Image> data) {
        List<Image> result = new ArrayList<>();

        for (Image img: data)
            if ( isWeightInSearchRange(img.getWeightInMb()) )
                result.add(img);

        return result;
    }

    private boolean isWeightInSearchRange(double weight) {
        return (weight >= leftBoundary) && (weight <= rightBoundary);
    }

    @Override
    public List<Image> searchInDB() throws SQLException {
        ImageDAO imageDAO = new ImageDAO();
        return imageDAO.getImagesByWeight(leftBoundary, rightBoundary);
    }
}
