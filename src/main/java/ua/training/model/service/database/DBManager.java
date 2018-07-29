package ua.training.model.service.database;

import ua.training.model.entity.Image;
import ua.training.model.service.factory.ImageMaker;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager ourInstance = new DBManager();
    private static final String URL = "jdbc:mysql://localhost:3306/imageDB" + "?useLegacyDatetimeCode=false"+"&amp"+  "&serverTimezone=UTC"+"&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String query = "select count(*) from images";
    private static final String query1 = "insert into imageDB.images (id, name, format, time, tag)" +
            "values (6, 'graf2', 'ai', '2002-12-28 13:45:58', '#my_work');";


    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {
    }

    public List<Image> getImagesFromDB(String query) {
        List<Image> result = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ImageMaker imageMaker;

            while (resultSet.next()){
                String name = resultSet.getString(2);
                String format = resultSet.getString(3);
                double weightInMb = resultSet.getDouble(4);
                LocalDateTime localDateTime = timeFromDBToLocalDateTime(resultSet.getString(5));
                String tag = resultSet.getString(6);

                imageMaker = ImageMaker.getImageMakerByFormat(format);

                result.add(imageMaker.makeImage(name,format,weightInMb,localDateTime,tag));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private LocalDateTime timeFromDBToLocalDateTime(String time) {
        return LocalDateTime.of(
                Integer.parseInt(time.substring(0,4)),
                Integer.parseInt(time.substring(5,7)),
                Integer.parseInt(time.substring(8,10)),
                Integer.parseInt(time.substring(11,13)),
                Integer.parseInt(time.substring(14,16)),
                Integer.parseInt(time.substring(17,19))
        );
    }

    public static void main(String[] args){
        DBManager dbManager = new DBManager();
        List<Image> list = dbManager.getImagesFromDB("select * from images");
        System.out.println(list);
//        String time = "2018-07-29 20:37:13";
//        System.out.println(Integer.parseInt(time.substring(0, 4)));
//        System.out.println(Integer.parseInt(time.substring(5, 7)));
//        System.out.println(Integer.parseInt(time.substring(8, 10)));
//        System.out.println(Integer.parseInt(time.substring(11,13)));
//        System.out.println(Integer.parseInt(time.substring(14,16)));
//        System.out.println(Integer.parseInt(time.substring(17,19)));



    }
}
