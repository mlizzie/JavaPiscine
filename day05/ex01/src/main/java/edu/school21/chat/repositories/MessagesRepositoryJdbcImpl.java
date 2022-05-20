package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private  DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {

        this.ds = ds;
    }

    @Override
    public Optional<Message> findId(int id) throws SQLException {

        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        Optional<Message> optionalMessage;

        String query = "SELECT * FROM chat.msgs WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        User user = new User((long) 1,"t", "123",null, null);
        Chatroom chatroom = new Chatroom((long)1, "NAME", null, null);
        optionalMessage = Optional.of(new Message((long) resultSet.getInt(1),
                user, chatroom, resultSet.getString("message"),
                resultSet.getTimestamp("time_msg").toLocalDateTime()));
        connection.close();
        return optionalMessage;
    }
}
