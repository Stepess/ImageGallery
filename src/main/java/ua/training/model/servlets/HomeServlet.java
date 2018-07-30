package ua.training.model.servlets;

import ua.training.model.entity.Image;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private static DBManager dbManager;
    private String home = "/WEB-INF/home.jsp";


    @Override
    public void init() throws ServletException {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<Image> list = (List<Image>) getServletContext().getAttribute("imageList");
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



        req.setAttribute("images", list);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(home);
        requestDispatcher.forward(req,resp);
    }



}
