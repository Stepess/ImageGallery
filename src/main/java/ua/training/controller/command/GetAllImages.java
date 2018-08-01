package ua.training.controller.command;

import ua.training.model.service.database.ImageDAO;
import ua.training.model.service.database.SlideShows;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class GetAllImages implements Command {
    SlideShows slideShows = SlideShows.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.setAttribute("images", new ImageDAO().getAllImages());
            request.setAttribute("slideshows", slideShows.getSlideShows().values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/index.jsp";
    }
}
