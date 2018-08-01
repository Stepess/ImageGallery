package ua.training.model.service.search;

import java.sql.SQLException;
import java.util.List;

public interface Service <T> {
    List<T> search(List<T> data);
    List<T> searchInDB() throws SQLException;
}
