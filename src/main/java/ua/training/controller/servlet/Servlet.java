package ua.training.controller.servlet;

import ua.training.controller.command.*;
import ua.training.model.service.database.ImageDAO;
import ua.training.model.service.regex.Regex;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Servlet extends HttpServlet {
    private String home = "/WEB-INF/index.jsp";
    List<String> errors;
    private Map<String, Command> commands = new HashMap<>();


    @Override
    public void init() throws ServletException {
        commands.put("add", new AddImage());
        commands.put("getAll", new GetAllImages());
        commands.put("sortByWeight", new SortByWeight());
        commands.put("sortByTime", new SortByTime());
        commands.put("sortByTag", new SortByTag());
        commands.put("search", new SearchImage());
        try {
            commands.put("createSlideShow", new CreateSlideShow(new ImageDAO().getAllImages()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getRequestURI();

        path = path.replaceAll(".*/", "");
        System.out.println(path);
        String page;
        if (commands.keySet().contains(path)) {
            Command command = commands.get(path);
            page = command.execute(req);
        } else {
            page = home;
        }

        try {
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private boolean isNameValid(String name) {
        if (name.matches(Regex.NAME_REGEX))
            return true;
        else {
            errors.add(String.format("Sorry, name %s is out format", name));
            return false;
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
