package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.service.SearcherByTag;
import ua.training.model.service.SearcherByTimeOfLastEdit;
import ua.training.model.service.SearcherByWeight;
import ua.training.model.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private String search = "/WEB-INF/search.jsp";
    private List<String> errors;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", errors);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(search);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Image> result = (List<Image>) getServletContext().getAttribute("imageList");
        errors = new ArrayList<>();
        result = searchByWeight(req, result);
        result = searchByTime(req, result);
        result = searchByTag(req, result);
        getServletContext().setAttribute("imageList", result);
        doGet(req, resp);
    }

    private List<Image> searchByTag(HttpServletRequest req, List<Image> result) {
        Service<Image> service;

        if (!req.getParameter("tag").equals("")){
            service = new SearcherByTag(req.getParameter("tag"));
            result = service.search(result);
        }
        return result;
    }

    private List<Image> searchByTime(HttpServletRequest req, List<Image> data) {
        List<Image> result = new ArrayList<>();

        Service<Image> service ;

        String time = req.getParameter("leftTimeBoundary");
        LocalDateTime ltb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        time = req.getParameter("rightTimeBoundary");
        LocalDateTime rtb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        try {
            service = new SearcherByTimeOfLastEdit(ltb, rtb);
            result = service.search(data);
        }
        catch (IllegalArgumentException ex){
            errors.add(ex.getMessage());
            return data;
        }
        return result;
    }

    private List<Image> searchByWeight(HttpServletRequest req, List<Image> data) {
        List<Image> result = new ArrayList<>();
        try {
        Service<Image> service = new SearcherByWeight(Double.parseDouble(req.getParameter("leftWeightBoundary")),
                Double.parseDouble(req.getParameter("rightWeightBoundary")));
        result = service.search(data);
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
            errors.add(ex.getMessage());
            return data;
        }
        return result;
    }
}


/*List<Image> result = new ArrayList<>();
        List<Image> tempResult = new ArrayList<>();
        errors = new ArrayList<>();
//        result = searchByWeight(req, result);
//        result = searchByTime(req, result);
//        result = searchByTag(req, result);

        Service<Image> service;
        if (!req.getParameter("tag").equals("")){
            service = new SearcherByTag(req.getParameter("tag"));
            try {
                result = service.searchInDB();
            } catch (SQLException e) {
                errors.add("Something wrong with database");
            }
        }




        String time = req.getParameter("leftTimeBoundary");
        LocalDateTime ltb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        time = req.getParameter("rightTimeBoundary");
        LocalDateTime rtb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);

        service = new SearcherByTimeOfLastEdit(ltb, rtb);
        try {
            tempResult = service.searchInDB();
        } catch (SQLException e) {
            errors.add("Something wrong with database");
        }


        for (Image img: result){
            if (!tempResult.contains(img))
                result.remove(img);
        }
        tempResult.clear();



        service = new SearcherByWeight(Double.parseDouble(req.getParameter("leftWeightBoundary")),
                Double.parseDouble(req.getParameter("rightWeightBoundary")));
        try {
            tempResult = service.searchInDB();
        } catch (SQLException e) {
            errors.add("Something wrong with database");
        }
        System.out.println(tempResult);
        System.out.println(result);

        for (Image img: result){
            if (!tempResult.contains(img))
                result.remove(img);
        }
        tempResult.clear();

        System.out.println(result);
        getServletContext().setAttribute("imageList", result);
        doGet(req, resp);*/