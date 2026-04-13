package jdbc.dao;

import jdbc.connection.DatabaseConnection;
import jdbc.model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements GenericDAO<ProductModel, Integer> {
    private final DatabaseConnection databaseConnection;

    public ProductDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public boolean insert(ProductModel product) {
        String sql = "INSERT INTO Production.Product (ProductName, SupplierId, CategoryId, UnitPrice, Discontinued) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getProductName());
                statement.setInt(2, product.getSupplierId());
                statement.setInt(3, product.getCategoryId());
                statement.setBigDecimal(4, product.getUnitPrice());
                statement.setBoolean(5, product.isDiscontinued());
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
    public ProductModel findById(Integer id) {
        String sql = "SELECT ProductId, ProductName, SupplierId, CategoryId, UnitPrice, Discontinued " +
                "FROM Production.Product WHERE ProductId = ?";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapProduct(resultSet);
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
    public List<ProductModel> findAll() {
        String sql = "SELECT ProductId, ProductName, SupplierId, CategoryId, UnitPrice, Discontinued FROM Production.Product";

        List<ProductModel> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapProduct(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection(connection);
            }
        }
        return products;
    }

    @Override
    public boolean update(ProductModel product) {
        String sql = "UPDATE Production.Product SET ProductName = ?, SupplierId = ?, CategoryId = ?, " +
                "UnitPrice = ?, Discontinued = ? WHERE ProductId = ?";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getProductName());
                statement.setInt(2, product.getSupplierId());
                statement.setInt(3, product.getCategoryId());
                statement.setBigDecimal(4, product.getUnitPrice());
                statement.setBoolean(5, product.isDiscontinued());
                statement.setInt(6, product.getProductId());
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
        String sql = "DELETE FROM Production.Product WHERE ProductId = ?";

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

    private ProductModel mapProduct(ResultSet resultSet) throws SQLException {
        return new ProductModel(
                resultSet.getInt("ProductId"),
                resultSet.getString("ProductName"),
                resultSet.getInt("SupplierId"),
                resultSet.getInt("CategoryId"),
                resultSet.getBigDecimal("UnitPrice"),
                resultSet.getBoolean("Discontinued")
        );
    }
}
