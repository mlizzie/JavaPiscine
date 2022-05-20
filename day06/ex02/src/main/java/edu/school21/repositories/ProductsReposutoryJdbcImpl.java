package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;

    public ProductsReposutoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {

        List<Product> products = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        while (resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
        }

        statement.close();
        connection.close();
        return products;
    }

    @Override
    public Optional<Product> findById(Integer id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE identifier = " + id + ";");

        if (!resultSet.next())
            throw new RuntimeException("object with specified id not found");

        Product product = new Product(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3));

        statement.close();
        connection.close();

        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product " +
                        "SET name = ?, price = ? " +
                        "WHERE identifier = ?;"
        );

        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getId());

        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into product VALUES (?, ?, ?);");
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Integer id) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM product " +
                        "WHERE identifier = ?;"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();

    }
}
