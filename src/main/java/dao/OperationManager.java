package dao;

import config.DatabaseConfig;
import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by boro on 23.03.15.
 */
@Repository
public class OperationManager{
    @Autowired
    private Connection connection;

    @Autowired
    private DatabaseConfig config;

    public Connection setConnection() throws IOException, SQLException, ClassNotFoundException {
        if (connection.isClosed()){
            connection = DriverManager.getConnection(config.getDbUrl());
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
