package jdbc.dao;

import java.util.List;

/**
 * Generic CRUD contract for DAO classes.
 *
 * @param <T>  entity type
 * @param <ID> primary key type
 */
public interface GenericDAO<T, ID> {
    boolean insert(T entity);
    T findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(ID id);
}
