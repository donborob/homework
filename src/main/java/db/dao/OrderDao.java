package db.dao;

import db.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by boro on 16.03.15.
 */
public class OrderDao implements Dao<Order>{
    public EntityManager  entityManager = Persistence.createEntityManagerFactory("PersistenceUnit").createEntityManager();
   @Override
    public void addOrUpdate(Order order){
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }
    @Override
    public void delete(int id){
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }
    @Override
    public Order get(long id){
        return entityManager.find(Order.class, id);
    }

    public Long getCount(){
        return (Long) entityManager.createQuery("select count(*) from Order").getResultList().get(0);
    }

    /*private static Connection getConnection() throws IOException, SQLException
     {
       Properties properties = new Properties();
        ClassLoader classLoader = StudentDao.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("derby.properties");
        properties.load(inputStream);
        Connection connection = DriverManager.getConnection(properties.getProperty("connectionUrl"));
        return connection;
    }
    public static void delete(long id) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "DELETE FROM ORDERS WHERE ID= "+id;
        statement.execute(sql);
    }

    public static  ResultSet read(long id) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM ORDERS WHERE ID= "+id;
        statement.execute(sql);
       return statement.getResultSet();
    }

    public static void updateSellerId(long id, String sellerId) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE ORDERS SET sellerId = "+sellerId +" WHERE id = " +id +")";
        statement.execute(sql);
    }
    public static void updateCustomerId(long id, String customerId) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE  ORDERS SET customerId = "+customerId +" WHERE id = " +id +")";
        statement.execute(sql);
    }
    public static void updateTotalAmount(long id, String totalAmount) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE  ORDERS SET totalAmount = "+totalAmount +" WHERE id = " +id +")";
        statement.execute(sql);
    }

    public static void create(int sellerId, int customerId, int totalAmount ) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "INSERT INTO ORDERS (sellerId,customerId,totalAmount) VALUES ("+sellerId+","+ customerId+","+totalAmount+")";
        statement.execute(sql);
*/
    }
