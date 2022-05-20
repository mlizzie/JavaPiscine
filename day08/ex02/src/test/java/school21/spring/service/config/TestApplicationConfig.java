package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@ComponentScan("school21.spring.service.services")
@ComponentScan("school21.spring.service.repositories")
public class TestApplicationConfig {

    @Bean("driverManger")
    public DataSource getDataSource(){
        EmbeddedDatabase build = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
        try (Connection connection = build.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("create table service.users (\n" +
                    "    identifier serial primary key,\n" +
                    "    email text,\n" +
                    "    password text\n" +
                    ");");

        } catch (SQLException e) {
            e.getErrorCode();
        }
        return build;
    }
    @Bean("hikariDataSource")
    public DataSource getEmbeddedDataSource(){
        EmbeddedDatabase build = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
        try (Connection connection = build.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("create table service.users (\n" +
                    "    identifier serial primary key,\n" +
                    "    email text,\n" +
                    "    password text\n" +
                    ");");

        } catch (SQLException e) {
            e.getErrorCode();
        }
        return build;
    }
}
