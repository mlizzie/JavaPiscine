package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("edu.school21.sockets")
public class SocketsApplicationConfig {

    @Bean("hikariDataSource")
    public DataSource getHikari() {
        HikariDataSource dataSource = new HikariDataSource();

        Properties properties = getProperties();

        dataSource.setJdbcUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.user"));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setDriverClassName(properties.getProperty("db.driver.name"));

        return dataSource;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(SocketsApplicationConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
