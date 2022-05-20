package school21.spring.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@ComponentScan("school21.spring.service")
@Configuration
public class ApplicationConfig {

    @Bean("driverManger")
    public DataSource getDriverManger() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        Properties properties = getProperties();

        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.user"));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setDriverClassName(properties.getProperty("db.driver.name"));

        return dataSource;
    }

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
            properties.load(ApplicationConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
