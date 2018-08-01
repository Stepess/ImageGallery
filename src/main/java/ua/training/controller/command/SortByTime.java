package ua.training.controller.command;

import ua.training.model.entity.Image;
import ua.training.model.service.comparators.ImageTimeOfLastEditComparator;
import ua.training.model.service.database.ImageDAO;
import ua.training.model.service.database.SlideShows;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SortByTime implements Command {
    SlideShows slideShows = SlideShows.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        List<Image> images = null;
        try {
            images = new ImageDAO().getAllImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(images, new ImageTimeOfLastEditComparator());
        request.setAttribute("images", images);
        request.setAttribute("slideshows", slideShows.getSlideShows().values());
        return "/WEB-INF/index.jsp";
    }
}
