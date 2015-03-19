import db.config.DatabaseConfiguration;
import db.dao.OrderDao;
import db.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Starter {
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);
    @Autowired
    private DatabaseConfiguration config;

    public DatabaseConfiguration getConfig() {
        return config;
    }

    public void setConfig(DatabaseConfiguration config) {
        this.config = config;
    }

    public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        Starter main = (Starter)context.getBean("starter");
      main.run();

    }
    public  void run() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        logger.info("url=" + config.getDbUrl());
        System.out.println("url=" + config.getDbUrl());
        createTables();
        OrderDao orderDao = new OrderDao();
        logger.info("Count1: "+orderDao.getCount());
        System.out.println("Count1: "+orderDao.getCount());
        orderDao.addOrUpdate(new Order(5, 6, 7));
        orderDao.addOrUpdate(new Order(7, 5, 2));
        logger.info("Count2: "+orderDao.getCount());
        System.out.println("Count2: "+orderDao.getCount());
    }

    public static void createTables() throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
      /*  Properties p = new Properties();
        ClassLoader classLoader = Starter.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("derby-env.properties");
        p.load(inputStream);*/
        Connection connection = DriverManager.getConnection("jdbc:derby:boro.db_dev;create=true");
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE STUDENTS ("
                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "firstname VARCHAR(255) not NULL,"
                + "lastname VARCHAR(255) NOT NULL ,"
                + "age INTEGER,"
                + "PRIMARY KEY(id))";
      try {
          statement.execute(sql);
      }
      catch (SQLException e){
          if (!e.getSQLState().equals("X0Y32")) {
              throw e;
          }
      }
        sql = "CREATE TABLE ORDERS ("
                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "sellerId INTEGER not NULL,"
                + "cistomerId INTEGER not NULL,"
                + "totalAmount INTEGER,"
                + "PRIMARY KEY(id))";
        try {
            statement.execute(sql);
        }
        catch (SQLException e){
            if (!e.getSQLState().equals("X0Y32")) {
                throw e;
            }
        }
        connection.close();
    }
}
