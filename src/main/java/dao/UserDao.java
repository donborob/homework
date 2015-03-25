package dao;

import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by boro on 23.03.15.
 */
public class UserDao implements Dao<User> {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OperationManager operationManager = (OperationManager) context.getBean("operationManager");

    @Override
    public void create(User user) throws IOException, SQLException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "INSERT INTO USERS (id, firstname, lastname, age) VALUES ( DEFAULT, '"+user.getFirstname()
                +"', '"+user.getLastname()+"', "+user.getAge()+")";
        operationManager.doQuery(sql);
        operationManager.closeConnection();
    }

    @Override
    public User get(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT firstname,lastname,age FROM USERS WHERE id = "+ id;
        ResultSet resultSet = operationManager.getResultSet(sql);
       User user = new User();

        if (resultSet.next()) {
            user.setId(id);
            user.setAge(Integer.parseInt(resultSet.getString(3)));
            user.setFirstname(resultSet.getString(1));
            user.setLastname(resultSet.getString(2));
        }
        operationManager.closeConnection();
        return user;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<User>();
        Connection connection = operationManager.setConnection();
        String sql = "SELECT firstname,lastname,age FROM USERS";
        ResultSet resultSet = operationManager.getResultSet(sql);

        int i = 1;
        while (resultSet.next()) {
            User user = new User();
            user.setId(i);
            user.setAge(Integer.parseInt(resultSet.getString(3)));
           user.setFirstname(resultSet.getString(1));
           user.setLastname(resultSet.getString(2));
           users.add(user);
            i++;
        }
        operationManager.closeConnection();
        return users;
    }

    @Override
    public void update(User user, int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "UPDATE USERS SET firstname = '"+user.getFirstname() +"', lastname = '"
                +user.getLastname()+"', age = "+user.getAge()+" WHERE id = "+id;
        operationManager.doQuery(sql);
        operationManager.closeConnection();
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "DELETE FROM  APP.USERS WHERE id = " +id;
        operationManager.doQuery( sql);
        operationManager.closeConnection();
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT COUNT (*) FROM  USERS";
        ResultSet resultSet= operationManager.getResultSet(sql);
        resultSet.next();
        int count = Integer.parseInt(resultSet.getString(1));
        operationManager.closeConnection();
        return count;
    }
}
