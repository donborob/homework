package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by boro on 23.03.15.
 */
@Configuration
public class DatabaseConfig {
    @Value("connectionUrl") // When set the value via derby-env.properties or/and ${} throws driver not found exception
    private String dbUrl;

    public String getDbUrl() throws ClassNotFoundException {
        return dbUrl;
    }
}
