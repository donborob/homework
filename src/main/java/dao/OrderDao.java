package dao;

import dao.manager.OperationManager;
import dao.mapper.OrderMapper;
import dao.mapper.UserMapper;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by boro on 23.03.15.
 */
@Repository
public class OrderDao implements Dao<Order> {
    @Autowired
    OperationManager operationManager;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Order order) throws IOException, SQLException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "INSERT INTO ORDERS VALUES (DEFAULT,?,?,?)";
        jdbcTemplate.update(sql, order.getSellerId(), order.getCustomerId(), order.getTotalAmount());
        operationManager.closeConnection();
    }

    @Override
    public Order get(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT sellerId,customerId,totalAmount FROM ORDERS WHERE id = " + id;
        Order order = (Order) jdbcTemplate.query(sql, new OrderMapper()).get(0);
        operationManager.closeConnection();
        return order;
    }

    @Override
    public List<Order> getAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT * FROM ORDERS";
        List<Order> orders = jdbcTemplate.query(sql, new OrderMapper());
        operationManager.closeConnection();
        return orders;
    }

    @Override
    public void update(Order order, int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        getCount();
        String sql = "UPDATE ORDERS SET sellerId = ?, customerId = ?,  totalAount = ?  WHERE id = ?";
        jdbcTemplate.update(sql, order.getSellerId(), order.getCustomerId(), order.getTotalAmount(), id);

        operationManager.closeConnection();
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "DELETE FROM  APP.ORDERS WHERE id = ?";
        jdbcTemplate.update(sql, id);
        operationManager.closeConnection();
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT COUNT (*) FROM  ORDERS";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        operationManager.closeConnection();
        return count;
    }
}
