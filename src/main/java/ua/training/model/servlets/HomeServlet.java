package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.entity.SlideShow;
import ua.training.model.service.ImageTagComparator;
import ua.training.model.service.ImageTimeOfLastEditComparator;
import ua.training.model.service.ImageWeightComparator;
import ua.training.model.service.Service;
import ua.training.model.service.database.DBManager;
import ua.training.model.service.database.ImageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private static DBManager dbManager;
    private String home = "/WEB-INF/home.jsp";
    List<Image> list;
    List<SlideShow> slideShows;


    @Override
    public void init() throws ServletException {
        slideShows = (List<SlideShow>) getServletContext().getAttribute("slideShowList");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Image> imagesToAdd = new ArrayList<>();
        for (Image img: list){
            if ( req.getParameter(img.getName()) != null )
                imagesToAdd.add(img);
        }
        SlideShow slideShow = new SlideShow("name", "avi", 0.0, LocalDateTime.now(), imagesToAdd);
        slideShows.add(slideShow);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        list = (List<Image>) getServletContext().getAttribute("imageList");
        String action = req.getParameter("sort");
        if (action != null) {

            switch (action) {
                case ("byWeight"):
                    Collections.sort(list, new ImageWeightComparator());
                    break;
                case ("byTime"):
                    Collections.sort(list, new ImageTimeOfLastEditComparator());
                    break;
                case ("byTag"):
                    Collections.sort(list, new ImageTagComparator());
                    break;
                default:
                    break;
            }
        }

        action = req.getParameter("search");
        if (action != null){
            ImageDAO imageDAO = new ImageDAO();
            list = null;
            try {
                list = imageDAO.getAllImages();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().setAttribute("imageList", list);
        }

        action = req.getParameter("delete");
        if (action != null){
            ImageDAO imageDAO = new ImageDAO();
            Image imageToDelete;
            try {
                imageToDelete = imageDAO.getImageByName(action);
                imageDAO.deleteImage(imageToDelete);
                System.out.println(list.remove(imageToDelete));
                getServletContext().setAttribute("imageList", list);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        req.setAttribute("images", list);
        req.setAttribute("slideshows", slideShows);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(home);
        requestDispatcher.forward(req,resp);
    }



}
