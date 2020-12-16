package dao;

import java.util.List;

public interface CRUDRepository<T> {
    T findById(int id);
    List<T> findAll();
    boolean create(T obj);
    boolean update(int id, T obj);
    boolean delete(int id);
}
