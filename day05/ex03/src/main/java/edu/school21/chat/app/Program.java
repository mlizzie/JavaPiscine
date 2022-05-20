package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.time.LocalDateTime;
import java.util.Optional;


public class Program {

    public static void main(String[] args) {

        MessagesRepository msg;
        try {

            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl("jdbc:postgresql://localhost:5433/postgres");
            ds.setUsername("postgres");
            ds.setPassword("123456");

            msg = new MessagesRepositoryJdbcImpl(ds);


            Optional<Message> messageOptional = msg.findId(10);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setText("Bye");
                message.setLocalDateTime(LocalDateTime.now());
                msg.update(message);
            }
        }
        catch(NotSavedSubEntityException e){
                e.printStackTrace();
        }
        catch(Exception e){
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(-1);
        }
    }
}
