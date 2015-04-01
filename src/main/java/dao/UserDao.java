package dao;

import dao.manager.OperationManager;
import dao.mapper.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by boro on 23.03.15.
 */
@Repository
public class UserDao implements Dao<User> {
    @Autowired
    OperationManager operationManager;
    Map<String, Object> map;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.map = new HashMap<String, Object>();


    }

    @Override
    public void create(User user) throws IOException, SQLException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "INSERT INTO USERS VALUES (DEFAULT,:firstname,:lastname,:age)";
        map.put("firstname", user.getFirstname());
        map.put("lastname", user.getLastname());
        map.put("age", user.getAge());
        jdbcTemplate.update(sql, map) ;
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
        String sql = "UPDATE USERS SET firstname = :firstname , lastname = :lastname,  age = :age  WHERE id = :id";
        map.put("firstname", user.getFirstname());
        map.put("lastname", user.getLastname());
        map.put("age", user.getAge());
        map.put("id",user.getId()+1);
        jdbcTemplate.update(sql,map) ;

        operationManager.closeConnection();
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "DELETE FROM  APP.USERS WHERE id = :id";
        map.put("id",id);
        jdbcTemplate.update(sql,map) ;
        operationManager.closeConnection();
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT COUNT (*) FROM  USERS";
        int count = jdbcTemplate.queryForObject(sql,map, Integer.class);
        operationManager.closeConnection();
        return count;
    }
}
