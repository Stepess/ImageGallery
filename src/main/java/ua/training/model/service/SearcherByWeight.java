package ua.training.model.service;

import ua.training.model.entity.Image;

import java.util.List;

public class SearcherByWeight implements Service<Image> {
    private double leftBoundary;
    private double rightBoundary;

    public void setSearchDiapason(double leftBoundary, double rightBoundary){
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }

    @Override
    public List<Image> search(List<Image> data) {
        return null;
    }
}
