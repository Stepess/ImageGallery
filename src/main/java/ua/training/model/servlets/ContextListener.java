package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.entity.SlideShow;
import ua.training.model.service.database.ImageDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContextListener implements ServletContextListener {

    private List<Image> imageList;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        imageList = new ArrayList<>();
        ImageDAO imageDAO = new ImageDAO();
        try {
            imageList = imageDAO.getAllImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("imageList", imageList);
        servletContext.setAttribute("slideShowList", new ArrayList<SlideShow>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        imageList = null;
    }

}
