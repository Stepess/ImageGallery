package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.VectorImage;
import ua.training.model.service.database.DBManager;
import ua.training.model.service.factory.ImageMaker;
import ua.training.model.service.regex.Regex;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

public class AddServlet extends HttpServlet {
    private List<String> errorMessages;
    private String add = "/WEB-INF/add.jsp";
    private static DBManager dbManager;
    Map<String, String> data = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        dbManager = DBManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("data", data);
        req.setAttribute("errors", errorMessages);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(add);
        requestDispatcher.forward(req, resp);
        data.clear();
        errorMessages.clear();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        data.put("name", req.getParameter("name"));
        data.put("format", req.getParameter("format"));
        data.put("weight", req.getParameter("weight"));
        data.put("tag", req.getParameter("tag"));
        errorMessages = new ArrayList<>();

        if (isDataCorrect(data, errorMessages) && isDataUnique(data, errorMessages)){//double check
            try{
                ImageMaker imageMaker = ImageMaker.getImageMakerByFormat(data.get("format"));
                Image image = imageMaker.makeImage(
                        data.get("name"),
                        data.get("format"),
                        Double.parseDouble(data.get("weight")),
                        LocalDateTime.now(),
                        data.get("tag"));
                dbManager.insertImageInDB(image);
            }
            catch (SQLException ex){
                errorMessages.add("Sorry! Something went wrong. Please, resend your data.");
                doGet(req, resp);
            }
        }
        doGet(req, resp);
    }

    private boolean isDataCorrect(Map<String, String> data, List<String> errorMessages) {
        boolean isCorrect = true;

        if (!data.get("name").matches(Regex.NAME_REGEX)){
            errorMessages.add(String.format("Name %s is out of format!", data.get("name")));
            isCorrect = false;
        }

        if (!(RasterImage.RasterFormat.contains(data.get("format")) ||
                VectorImage.VectorFormat.contains(data.get("format")))){
            errorMessages.add(String.format("Format %s is unsupported!", data.get("format")));
            isCorrect = false;
        }

        if (!data.get("weight").matches(Regex.WEIGHT_REGEX)){
            errorMessages.add(String.format("Weight %s is out of format!", data.get("weight")));
            isCorrect = false;
        }

        if (!data.get("tag").matches(Regex.TAG_REGEX)){
            errorMessages.add(String.format("Tag %s is out of format!", data.get("tag")));
            isCorrect = false;
        }

        return isCorrect;
    }

    private boolean isDataUnique(Map<String, String> data, List<String> errorMessages){
        boolean isUnique = true;

        List<Image> images = dbManager.getImagesFromDB("select * from images");

        for (Image image: images)
            if (image.getName().equals(data.get("name"))){
                errorMessages.add(String.format("Sorry! Name %s is already taken!", data.get("name")));
                isUnique = false;
                break;
            }

        return isUnique;
    }
}


