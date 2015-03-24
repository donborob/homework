package dao;

import config.DatabaseConfig;
import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by boro on 23.03.15.
 */
public class OperationManager{
    @Autowired
    private DatabaseConfig config;

    public Connection setConnection() throws IOException, SQLException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection("jdbc:derby:dbDev;create=true");// When set the value via derby-env.properties
        // or/and ${} throws driver not found exception
        return connection;
    }
    ResultSet getResultSet(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         return resultSet;
    }
    void doQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
       statement.executeUpdate(query);
    }

    public void closeConnection(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
