package ua.training.model.service;

import java.util.List;

public interface Service <T> {
    List<T> search(List<T> data);
    List<T> searchInDB();
}
