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
import java.util.List;

public class SearchServlet extends HttpServlet {
    private String search = "/WEB-INF/search.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(search);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Image> result;

        Service<Image> service = new SearcherByWeight(Double.parseDouble(req.getParameter("leftWeightBoundary")),
                Double.parseDouble(req.getParameter("rightWeightBoundary")));
        result = service.search((List<Image>) getServletContext().getAttribute("imageList"));



        String time = req.getParameter("leftTimeBoundary");
        LocalDateTime ltb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        time = req.getParameter("rightTimeBoundary");
        LocalDateTime rtb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        service = new SearcherByTimeOfLastEdit(ltb, rtb);

        result = service.search(result);

        if (req.getParameter("tag") != ""){
            service = new SearcherByTag(req.getParameter("tag"));
            result = service.search(result);
        }


        getServletContext().setAttribute("imageList", result);
        doGet(req, resp);
    }
}
