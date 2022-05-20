package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        MessagesRepositoryJdbcImpl msg;
        try {

            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl("jdbc:postgresql://localhost:5433/postgres");
            ds.setUsername("postgres");
            ds.setPassword("123456");

            msg =  new MessagesRepositoryJdbcImpl(ds);

            User creator = new User(2L, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;
            Chatroom room = new Chatroom((long) 4, "room", creator, new ArrayList());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            msg.save(message);
            System.out.println(message.getMessageId());
        }
        catch (NotSavedSubEntityException e){
            e.printStackTrace();
        }
        catch (Exception e){
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(-1);
        }

    }
}
