package jdbc.dao;

import java.sql.*;
import java.util.List;

/**
 * GenericDAO.java - Base CRUD interface.
 * @author Person 3 - [Your Name]
 * @version 1.0
 * AI Disclosure: [Note any AI tools used]
 */
public interface GenericDAO<T> {
    boolean insert(T entity) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(int id) throws SQLException;
}
