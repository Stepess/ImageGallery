package ua.training.controller.command;

import ua.training.model.entity.Image;
import ua.training.model.entity.RasterImage;
import ua.training.model.entity.VectorImage;
import ua.training.model.service.database.ImageDAO;
import ua.training.model.service.factory.ImageMaker;
import ua.training.model.service.regex.Regex;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddImage implements Command {
    Map<String, String> data = new HashMap<>();
    boolean addStatus;

    @Override
    public String execute(HttpServletRequest request) {
        List<String> errorMessages = new ArrayList<>();
        getDataFromReq(request);
        request.setAttribute("data", data);
        request.setAttribute("errors", errorMessages);
        request.setAttribute("formats", getAllImageFormat());
        if (isDataNotNull()) {
            if (!data.isEmpty() && isDataCorrect(data, errorMessages) && isDataUnique(data, errorMessages)) {//double check
                try {
                    addImageToDB();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    errorMessages.add("Sorry! Something went wrong. Please, resend your data.");
                    return "/WEB-INF/add.jsp";
                }
                if (addStatus) {
                    request.setAttribute("success", "Image successfully uploaded");
                    addStatus = false;
                    data.clear();
                }
            }
        }
        return "/WEB-INF/add.jsp";
    }

    private boolean isDataNotNull() {
        if (data.get("name") != null && data.get("format") != null && data.get("weight") != null && data.get("tag") != null)
            return true;
        return false;
    }

    private List<String> getAllImageFormat() {
        List<String> formats = new ArrayList<>();
        formats.addAll(RasterImage.RasterFormat.getAllFormats());
        formats.addAll(VectorImage.VectorFormat.getAllFormats());
        return formats;
    }

    private void getDataFromReq(HttpServletRequest req) {
        data.put("name", req.getParameter("name"));
        data.put("format", req.getParameter("format"));
        data.put("weight", req.getParameter("weight"));
        data.put("tag", req.getParameter("tag"));
    }

    private void addImageToDB() throws SQLException {
        ImageMaker imageMaker = ImageMaker.getImageMakerByFormat(data.get("format"));
        Image image = imageMaker.makeImage(
                data.get("name"),
                data.get("format"),
                Double.parseDouble(data.get("weight")),
                LocalDateTime.now(),
                data.get("tag"));
        ImageDAO imageDAO = new ImageDAO();
        addStatus = imageDAO.insertImage(image);
    }

    private boolean isDataCorrect(Map<String, String> data, List<String> errorMessages) {
        boolean isCorrect = true;
        if (!data.get("name").matches(Regex.NAME_REGEX)) {
            errorMessages.add(String.format("Name %s is out of format!", data.get("name")));
            isCorrect = false;
        }
        if (!data.get("weight").matches(Regex.WEIGHT_REGEX)) {
            errorMessages.add(String.format("Weight %s is out of format!", data.get("weight")));
            isCorrect = false;
        }
        if (!data.get("tag").matches(Regex.TAG_REGEX)) {
            errorMessages.add(String.format("Tag %s is out of format!", data.get("tag")));
            isCorrect = false;
        }
        return isCorrect;
    }

    private boolean isDataUnique(Map<String, String> data, List<String> errorMessages) {
        boolean isUnique = true;
        ImageDAO imageDAO = new ImageDAO();
        List<Image> images = null;
        try {
            images = imageDAO.getAllImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Image image : images)
            if (image.getName().equals(data.get("name"))) {
                errorMessages.add(String.format("Sorry! Name %s is already taken!", data.get("name")));
                isUnique = false;
                break;
            }

        return isUnique;
    }
}
