package jdbc.dao;

import jdbc.connection.DatabaseConnection;
import jdbc.model.SampleModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SampleDAO.java - Example DAO implementation. Duplicate for each table.
 * @author Person 3 - [Your Name]
 * @version 1.0
 */
public class SampleDAO implements GenericDAO<SampleModel> {
    private DatabaseConnection dbConnection;

    public SampleDAO() {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public boolean insert(SampleModel entity) throws SQLException {
        // TODO: INSERT query
        return false;
    }

    @Override
    public SampleModel findById(int id) throws SQLException {
        // TODO: SELECT by ID
        return null;
    }

    @Override
    public List<SampleModel> findAll() throws SQLException {
        // TODO: SELECT ALL
        return new ArrayList<>();
    }

    @Override
    public boolean update(SampleModel entity) throws SQLException {
        // TODO: UPDATE query
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // TODO: DELETE query
        return false;
    }
}
