package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by boro on 23.03.15.
 */
@Repository
public class UserDao implements Dao<User> {
    @Autowired
    OperationManager operationManager;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(User user) throws IOException, SQLException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "INSERT INTO USERS VALUES (DEFAULT,?,?,?)";
        jdbcTemplate.update(sql, user.getFirstname(), user.getLastname(), user.getAge());
        operationManager.closeConnection();
    }

    @Override
    public User get(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT firstname,lastname,age FROM USERS WHERE id = " + id;
        User user = (User) jdbcTemplate.query(sql, new UserMapper()).get(0);
        operationManager.closeConnection();
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        operationManager.closeConnection();
        return users;
    }

    @Override
    public void update(User user, int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        getCount();
        String sql = "UPDATE USERS SET firstname = ?, lastname = ?,  age = ?  WHERE id = ?";
        jdbcTemplate.update(sql, user.getFirstname(), user.getLastname(), user.getAge(), id);

        operationManager.closeConnection();
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "DELETE FROM  APP.USERS WHERE id = ?";
        jdbcTemplate.update(sql, id);
        operationManager.closeConnection();
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT COUNT (*) FROM  USERS";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        operationManager.closeConnection();
        return count;
    }
}
