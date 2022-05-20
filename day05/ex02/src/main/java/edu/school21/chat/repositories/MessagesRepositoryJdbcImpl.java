package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;


import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException, SQLException {

        if (message.getAuthor().getUserID() == 0 || message.getChatroom().getChatId() == 0) {
            throw new NotSavedSubEntityException();
        }
        String selectSQL = " INSERT into chat.msgs (room_id, sender, message, time_msg) VALUES (" +
                message.getChatroom().getChatId() + ", " +
                message.getAuthor().getUserID() + ", " +
                "'" + message.getText() + "', " +
                "'" + message.getLocalDateTime() + "');";

        Connection connection = ds.getConnection();

        PreparedStatement prepStmt = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);

        prepStmt.execute();
        ResultSet resultSet = prepStmt.getGeneratedKeys();
        resultSet.next();
        message.setMessageId((long) resultSet.getInt(1));
        return true;
    }
}
