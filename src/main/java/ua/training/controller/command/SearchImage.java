package ua.training.controller.command;

import ua.training.model.entity.Image;
import ua.training.model.service.database.ImageDAO;
import ua.training.model.service.search.SearcherByTag;
import ua.training.model.service.search.SearcherByTimeOfLastEdit;
import ua.training.model.service.search.SearcherByWeight;
import ua.training.model.service.search.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchImage implements Command {
    private List<String> errors;

    @Override
    public String execute(HttpServletRequest request) {
        List<Image> result = null;
        try {
            result = new ImageDAO().getAllImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        errors = new ArrayList<>();
        if (isDataNotNull(request)) {

            result = searchByWeight(request, result);
            result = searchByTime(request, result);
            result = searchByTag(request, result);


            if (errors.isEmpty() && !result.isEmpty()) {
                request.setAttribute("images", result);
                return "/WEB-INF/index.jsp";
            } else {
                request.setAttribute("errors", errors);
                return "/WEB-INF/search.jsp";
            }
        } else {
            return "/WEB-INF/search.jsp";
        }
    }

    private boolean isDataChanged(HttpServletRequest req) {
        if (!req.getParameter("tag").equals("") ||
                !req.getParameter("leftTimeBoundary").equals("1984-01-01T00:00:00") ||
                !req.getParameter("rightTimeBoundary").equals("2020-02-14T00:00:00") ||
                !req.getParameter("leftWeightBoundary").equals("0.0") ||
                !req.getParameter("rightWeightBoundary").equals("100.0"))
            return true;
        return false;
    }

    private boolean isDataNotNull(HttpServletRequest req) {

        if (req.getParameter("tag") != null &&
                req.getParameter("leftTimeBoundary") != null &&
                req.getParameter("rightTimeBoundary") != null &&
                req.getParameter("leftWeightBoundary") != null &&
                req.getParameter("rightWeightBoundary") != null)
            return true;
        return false;
    }

    private List<Image> searchByTag(HttpServletRequest req, List<Image> result) {
        Service<Image> service;

        if (!req.getParameter("tag").equals("")) {
            service = new SearcherByTag(req.getParameter("tag"));
            result = service.search(result);
        }
        return result;
    }

    private List<Image> searchByTime(HttpServletRequest req, List<Image> data) {
        List<Image> result = new ArrayList<>();

        Service<Image> service;

        String time = req.getParameter("leftTimeBoundary");
        LocalDateTime ltb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        time = req.getParameter("rightTimeBoundary");
        LocalDateTime rtb = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        try {
            service = new SearcherByTimeOfLastEdit(ltb, rtb);
            result = service.search(data);
        } catch (IllegalArgumentException ex) {
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
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            errors.add(ex.getMessage());
            return data;
        }
        return result;
    }
}
