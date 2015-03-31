package dao;

import model.Model;
import model.Order;
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

/**
 * Created by boro on 23.03.15.
 */
@Repository
public class OrderDao implements Dao<Order> {
    @Autowired
    OperationManager operationManager;


    @Override
    public void create(Order order) throws IOException, SQLException, ClassNotFoundException {
        operationManager.setConnection();
        String sql = "INSERT INTO ORDERS VALUES (DEFAULT, ?,?,?)";
        operationManager.closeConnection();
    }

    @Override
    public Order get(int id) throws SQLException, IOException, ClassNotFoundException {
        operationManager.setConnection();
        String sql = "SELECT sellerId,customerId,totalAmount FROM ORDERS WHERE ID = "+ id;
        operationManager.closeConnection();
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException, IOException, ClassNotFoundException {
        List<Order> orders = new ArrayList<Order>();
        operationManager.setConnection();
        String sql = "SELECT sellerId,customerId,totalAmount FROM ORDERS";
        operationManager.closeConnection();
        return orders;
    }

    @Override
    public void update(Order order, int id) throws SQLException, IOException, ClassNotFoundException {
        operationManager.setConnection();
        String sql = "UPDATE  ORDERS o SET o.sellerId = "+order.getSellerId()+", o.customerId = "
                +order.getCustomerId()+", o.totalAmount = "+order.getTotalAmount()+"WHERE id = "+id;
        operationManager.closeConnection();
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        operationManager.setConnection();
        String sql = "DELETE FROM ORDERS WHERE id = " + id;
        operationManager.closeConnection();
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        operationManager.setConnection();
        String sql = "SELECT COUNT(*) FROM  ORDERS";
        return 0;
    }
}
