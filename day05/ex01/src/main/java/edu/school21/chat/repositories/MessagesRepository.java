package edu.school21.chat.repositories;

import java.sql.SQLException;
import java.util.Optional;
import edu.school21.chat.models.Message;

public interface MessagesRepository {
    Optional<Message> findId(int id) throws SQLException;
}
