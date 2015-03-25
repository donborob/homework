package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by boro on 23.03.15.
 */
@Configuration
public class DatabaseConfig {
    @Value("${connectionUrl}")
    private String dbUrl;

    public String getDbUrl() throws ClassNotFoundException, SQLException {
        return dbUrl;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl);
    }
}
