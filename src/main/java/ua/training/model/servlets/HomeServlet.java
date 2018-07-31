package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.entity.SlideShow;
import ua.training.model.service.ImageTagComparator;
import ua.training.model.service.ImageTimeOfLastEditComparator;
import ua.training.model.service.ImageWeightComparator;
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
    private String home = "/WEB-INF/home.jsp";
    private ImageDAO imageDAO;
    List<Image> list;
    List<SlideShow> slideShows;
    List<String> errors;


    @Override
    public void init() throws ServletException {
        imageDAO = new ImageDAO();
        slideShows = (List<SlideShow>) getServletContext().getAttribute("slideShowList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createSlideShow(req);
        doGet(req, resp);
    }

    private void createSlideShow(HttpServletRequest req) {
        List<Image> imagesToAdd = new ArrayList<>();
        errors = new ArrayList<>();
        for (Image img: list){
            if ( req.getParameter(img.getName()) != null )
                imagesToAdd.add(img);
        }
        if (!imagesToAdd.isEmpty()){
            if (isNameUnique(req.getParameter("name")))
            slideShows.add(new SlideShow(req.getParameter("name"), req.getParameter("format"),
                    0.0, LocalDateTime.now(), imagesToAdd));
        }
        else {
            errors.add("You must choose at list one image to create a slideshow");
        }
        req.setAttribute("errors", errors);
    }

    private boolean isNameUnique(String name) {
        for (SlideShow slideShow: slideShows) {
            if (slideShow.getName().equals(name)) {
                errors.add(String.format("Unfortunately, name %s is already taken", name));
                return false;
            }
        }
        return true;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        list = (List<Image>) getServletContext().getAttribute("imageList");

        String action = req.getRequestURI();
        action = action.replace("/home/", "");

        switch (action) {
            case ("sortByWeight"):
                Collections.sort(list, new ImageWeightComparator());
                break;
            case ("sortByTime"):
                Collections.sort(list, new ImageTimeOfLastEditComparator());
                break;
            case ("sortByTag"):
                Collections.sort(list, new ImageTagComparator());
                break;
            case ("getAll"):{
                getAllImages();
                break;
            }
            case ("delete"):{
                deleteImage(req.getParameter("name"));
                resp.sendRedirect("/home/getAll");
                return;
            }
            default:
                break;
        }

        req.setAttribute("images", list);
        req.setAttribute("slideshows", slideShows);
        System.out.println(SlideShow.SlideShowFormat.getAllFormats());
        req.setAttribute("formats", SlideShow.SlideShowFormat.getAllFormats());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(home);
        requestDispatcher.forward(req,resp);
    }

    private void deleteImage(String nameToDelete) {
        try {
            Image imageToDelete = imageDAO.getImageByName(nameToDelete);
            imageDAO.deleteImage(imageToDelete);
            getServletContext().setAttribute("imageList", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getAllImages() {
        try {
            list = imageDAO.getAllImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().setAttribute("imageList", list);
    }


}
