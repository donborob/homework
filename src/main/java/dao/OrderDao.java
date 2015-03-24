package dao;

import model.Model;
import model.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by boro on 23.03.15.
 */
public class OrderDao implements Dao<Order> {
    OperationManager operationManager = new OperationManager();
    @Override
    public void create(Order order) throws IOException, SQLException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "INSERT INTO ORDERS VALUES ( DEFAULT, "+order.getSellerId()
                +","+order.getCustomerId()+","+order.getTotalAmount()+")";
        operationManager.doQuery(connection,sql);
        operationManager.closeConnection(connection);
    }

    @Override
    public Order get(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT sellerId,customerId,totalAmount FROM ORDERS WHERE ID = "+ id;
        ResultSet resultSet = operationManager.getResultSet(connection, sql);
        Order order = new Order();

            while (resultSet.next()) {
                order.setId(id);
                order.setTotalAmount(Integer.parseInt(resultSet.getString(3)));
                order.setSellerId(Integer.parseInt(resultSet.getString(1)));
                order.setCustomerId(Integer.parseInt(resultSet.getString(2)));
            }
        operationManager.closeConnection(connection);
        return order;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Order> orders = new ArrayList<Order>();
        Connection connection = operationManager.setConnection();
        String sql = "SELECT sellerId,customerId,totalAmount FROM ORDERS";
        ResultSet resultSet = operationManager.getResultSet(connection, sql);

        int i = 1;
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(i);
            order.setTotalAmount(Integer.parseInt(resultSet.getString(3)));
            order.setSellerId(Integer.parseInt(resultSet.getString(1)));
            order.setCustomerId(Integer.parseInt(resultSet.getString(2)));
            orders.add(order);
            i++;
        }
        operationManager.closeConnection(connection);
        return orders;
    }

    @Override
    public void update(Order order, int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "UPDATE  ORDERS o SET o.sellerId = "+order.getSellerId()+", o.customerId = "
                +order.getCustomerId()+", o.totalAmount = "+order.getTotalAmount()+"WHERE id = "+id;
        operationManager.doQuery(connection, sql);
        operationManager.closeConnection(connection);
    }

    @Override
    public void delete(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "DELETE FROM ORDERS WHERE id = " + id;
        operationManager.doQuery(connection, sql);
        operationManager.closeConnection(connection);
    }

    @Override
    public int getCount() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = operationManager.setConnection();
        String sql = "SELECT COUNT(*) FROM  ORDERS";
        ResultSet resultSet= operationManager.getResultSet(connection, sql);
        resultSet.next();
        int count = Integer.parseInt(resultSet.getString(1));
        operationManager.closeConnection(connection);
        return count;
    }
}
