package ua.training.model.service.search;

import ua.training.model.entity.Image;
import ua.training.model.service.database.ImageDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearcherByTimeOfLastEdit implements Service<Image>{
    private LocalDateTime leftBoundary;
    private LocalDateTime rightBoundary;

    public SearcherByTimeOfLastEdit(LocalDateTime leftBoundary, LocalDateTime rightBoundary) {
        if (rightBoundary.isBefore(leftBoundary))
            throw new IllegalArgumentException("Wrong boundaries!");
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }

    @Override
    public List<Image> search(List<Image> data) {
        List<Image> result = new ArrayList<>();

        for (Image img: data)
            if ( isLocalDateInRange(img.getTimeOfLastEdit()) )
                result.add(img);

        return result;
    }

    private boolean isLocalDateInRange(LocalDateTime localDateTime){
        return (localDateTime.isAfter(leftBoundary) || localDateTime.isEqual(leftBoundary) ) &&
                (localDateTime.isBefore(rightBoundary) || localDateTime.isEqual(leftBoundary));
    }

    @Override
    public List<Image> searchInDB() throws SQLException {
        ImageDAO imageDAO = new ImageDAO();
        return imageDAO.getImagesByTime(leftBoundary, rightBoundary);
    }
}
