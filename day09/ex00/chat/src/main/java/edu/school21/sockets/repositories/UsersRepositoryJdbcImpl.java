package edu.school21.sockets.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("jdbcStandard")
public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource")DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByName(String name) {

        User user = null ;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM service.users " +
                             "WHERE name = ?;"
             )){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(user);
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            String sqlQuiry = "SELECT * FROM service.users WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlQuiry);
            resultSet.next();

            user = new User(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            String sqlQuiry = "SELECT * FROM service.users";
            ResultSet resultSet = statement.executeQuery(sqlQuiry);

            while(resultSet.next())
                userList.add(new User(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        try (  Connection connection = dataSource.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement("insert into service.users (name,password) VALUES (?, ?);");) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String prepStatement = "UPDATE service.users " +
                "SET name = ? " +
                "WHERE id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(prepStatement)){

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getIdentifier());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM service.users " +
                             "WHERE id = ?;"
             )){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
