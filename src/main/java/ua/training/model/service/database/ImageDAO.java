package ua.training.model.service.database;

import ua.training.model.entity.Image;
import ua.training.model.service.factory.ImageMaker;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/imageDB" + "?useLegacyDatetimeCode=false"+"&amp"+  "&serverTimezone=UTC"+"&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        }
    }

    private void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    public boolean insertImage(Image image) throws SQLException {

        String query = "insert into images (name, format, weightInMb, time, tag)" +
                "values (?, ?, ?, ?, ?)";
        connect();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,image.getName());
            preparedStatement.setString(2,image.getFormat());
            preparedStatement.setDouble(3,image.getWeightInMb());
            preparedStatement.setString(
                    4,image.getTimeOfLastEdit().format(DateTimeFormatter.ofPattern("uuuu-MM-d HH:mm:ss")));
            preparedStatement.setString(5,image.getTag());
            //preparedStatement.execute();
            boolean rowInserted = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            disconnect();
            return rowInserted;

    }

    public List<Image> getAllImages() throws SQLException {
        List<Image> result = new ArrayList<>();

        connect();
        String query = "select * from images";
        try (Statement statement = connection.createStatement();
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
        disconnect();
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

    public boolean deleteImage(Image image) throws SQLException{
        String query = "delete from images where name = ?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, image.getName());

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowDeleted;
    }

    public Image getImageByName(String name) throws SQLException{
        String query = "select * from images where name = ?";

        connect();
        Image image = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    String format = resultSet.getString(3);
                    double weightInMb = resultSet.getDouble(4);
                    LocalDateTime localDateTime = timeFromDBToLocalDateTime(resultSet.getString(5));
                    String tag = resultSet.getString(6);

                    ImageMaker imageMaker = ImageMaker.getImageMakerByFormat(format);
                    image = imageMaker.makeImage(name, format, weightInMb, localDateTime, tag);
                }
            }
        }
        catch (SQLException ex){
            throw ex;
        }
        disconnect();
        return image;
    }
}
