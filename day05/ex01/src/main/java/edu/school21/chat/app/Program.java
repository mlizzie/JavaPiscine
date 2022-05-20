package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
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

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a message id");
            System.out.print("->");
            int id = scanner.nextInt();
            System.out.println(msg.findId(id).get());
        }
        catch (Exception e){
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(-1);
        }
    }
}
