package ua.training.controller.command;

import ua.training.model.entity.Image;
import ua.training.model.entity.SlideShow;
import ua.training.model.service.database.SlideShows;
import ua.training.model.service.regex.Regex;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateSlideShow implements Command {
    SlideShows slideShows = SlideShows.getInstance();
    List<Image> images;

    public CreateSlideShow(List<Image> images) {
        this.images = images;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("formats", SlideShow.SlideShowFormat.values());
        request.setAttribute("images", images);
        List<String> errorMessages = new ArrayList<>();
        request.setAttribute("errors", errorMessages);
        if (isDataNotNull(request)) {
            if (isNameUnique(request.getParameter("name"), errorMessages)) {
                List<Image> imagesToAdd = new ArrayList<>();
                for (Image img : images) {
                    if (request.getParameter(img.getName()) != null)
                        imagesToAdd.add(img);
                }
                if (!imagesToAdd.isEmpty()) {
                    slideShows.add(new SlideShow(request.getParameter("name"), request.getParameter("format"),
                            0.0, LocalDateTime.now(), imagesToAdd));
                    request.setAttribute("success", "Slide show successfully created");
                } else {
                    errorMessages.add("You must choose at list one image to create a slideshow");

                }
                request.setAttribute("errors", errorMessages);
            }
        } else {
            return "/WEB-INF/create.jsp";
        }
        return "/WEB-INF/create.jsp";
    }

    private boolean isDataNotNull(HttpServletRequest request) {
        if (request.getParameter("name") != null && request.getParameter("format") != null)
            return true;
        return false;
    }

    private boolean isNameUnique(String name, List<String> errorMessages) {
        if (name != null && slideShows.isNameUnique(name) && name.matches(Regex.NAME_REGEX)) {
            return true;
        }
        errorMessages.add(("Unfortunately, name is already taken or out of format"));
        return false;
    }
}
