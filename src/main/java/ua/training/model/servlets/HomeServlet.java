package ua.training.model.servlets;

import ua.training.model.entity.Image;
import ua.training.model.service.database.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private static DBManager dbManager;
    private String home = "/WEB-INF/home.jsp";

    @Override
    public void init() throws ServletException {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dbManager = DBManager.getInstance();
        List<Image> list = dbManager.getImagesFromDB("select * from images");
        //req.setAttribute("images", );

            StringBuffer sb = new StringBuffer("<table>");
            for (int index = 0; index < list.size()-1; index++) {


                sb.append("<tr><td>");
                sb.append(list.get(index));
                sb.append("</td></tr>");
            }
            sb.append("</table>");
            resp.getWriter().println(sb.toString());


    }

    public static void main(String[] args){
        dbManager = DBManager.getInstance();
        List<Image> list = dbManager.getImagesFromDB("select * from images");
        System.out.println(list);
    }


}
