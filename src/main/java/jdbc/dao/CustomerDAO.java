package jdbc.dao;

import jdbc.connection.DatabaseConnection;
import jdbc.model.CustomerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements GenericDAO<CustomerModel, Integer> {
    private final DatabaseConnection databaseConnection;

    public CustomerDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public boolean insert(CustomerModel customer) {
        String sql = "INSERT INTO Sales.Customer " +
                "(CustomerCompanyName, CustomerContactName, CustomerContactTitle, CustomerAddress, " +
                "CustomerCity, CustomerRegion, CustomerPostalCode, CustomerCountry, CustomerPhoneNumber, CustomerFaxNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getCustomerCompanyName());
                statement.setString(2, customer.getCustomerContactName());
                statement.setString(3, customer.getCustomerContactTitle());
                statement.setString(4, customer.getCustomerAddress());
                statement.setString(5, customer.getCustomerCity());
                statement.setString(6, customer.getCustomerRegion());
                statement.setString(7, customer.getCustomerPostalCode());
                statement.setString(8, customer.getCustomerCountry());
                statement.setString(9, customer.getCustomerPhoneNumber());
                statement.setString(10, customer.getCustomerFaxNumber());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
    }

    @Override
    public CustomerModel findById(Integer id) {
        String sql = "SELECT CustomerId, CustomerCompanyName, CustomerContactName, CustomerContactTitle, " +
                "CustomerAddress, CustomerCity, CustomerRegion, CustomerPostalCode, CustomerCountry, " +
                "CustomerPhoneNumber, CustomerFaxNumber FROM Sales.Customer WHERE CustomerId = ?";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapCustomer(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
        return null;
    }

    @Override
    public List<CustomerModel> findAll() {
        String sql = "SELECT CustomerId, CustomerCompanyName, CustomerContactName, CustomerContactTitle, " +
                "CustomerAddress, CustomerCity, CustomerRegion, CustomerPostalCode, CustomerCountry, " +
                "CustomerPhoneNumber, CustomerFaxNumber FROM Sales.Customer";

        List<CustomerModel> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(mapCustomer(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
        return customers;
    }

    @Override
    public boolean update(CustomerModel customer) {
        String sql = "UPDATE Sales.Customer SET CustomerCompanyName = ?, CustomerContactName = ?, " +
                "CustomerContactTitle = ?, CustomerAddress = ?, CustomerCity = ?, CustomerRegion = ?, " +
                "CustomerPostalCode = ?, CustomerCountry = ?, CustomerPhoneNumber = ?, CustomerFaxNumber = ? " +
                "WHERE CustomerId = ?";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getCustomerCompanyName());
                statement.setString(2, customer.getCustomerContactName());
                statement.setString(3, customer.getCustomerContactTitle());
                statement.setString(4, customer.getCustomerAddress());
                statement.setString(5, customer.getCustomerCity());
                statement.setString(6, customer.getCustomerRegion());
                statement.setString(7, customer.getCustomerPostalCode());
                statement.setString(8, customer.getCustomerCountry());
                statement.setString(9, customer.getCustomerPhoneNumber());
                statement.setString(10, customer.getCustomerFaxNumber());
                statement.setInt(11, customer.getCustomerId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Sales.Customer WHERE CustomerId = ?";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
    }

    private CustomerModel mapCustomer(ResultSet resultSet) throws SQLException {
        return new CustomerModel(
                resultSet.getInt("CustomerId"),
                resultSet.getString("CustomerCompanyName"),
                resultSet.getString("CustomerContactName"),
                resultSet.getString("CustomerContactTitle"),
                resultSet.getString("CustomerAddress"),
                resultSet.getString("CustomerCity"),
                resultSet.getString("CustomerRegion"),
                resultSet.getString("CustomerPostalCode"),
                resultSet.getString("CustomerCountry"),
                resultSet.getString("CustomerPhoneNumber"),
                resultSet.getString("CustomerFaxNumber")
        );
    }
}
