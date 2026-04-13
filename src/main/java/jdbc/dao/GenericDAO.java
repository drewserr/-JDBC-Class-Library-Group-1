package jdbc.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    boolean insert(T entity);
    T findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(ID id);
}
