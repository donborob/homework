package db.dao;

import db.entity.Order;
import db.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by boro on 16.03.15.
 */
public class StudentDao implements Dao<Student>{
    public EntityManager entityManager = Persistence.createEntityManagerFactory("PersistenceUnit").createEntityManager();
    @Override
    public void addOrUpdate(Student student){
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }
    @Override
    public void delete(int id){
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }
    @Override
    public Student get(long id){
        return entityManager.find(Student.class, id);
    }




  private Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        ClassLoader classLoader = StudentDao.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("derby.properties");
        properties.load(inputStream);
        Connection connection = DriverManager.getConnection(properties.getProperty("connectionUrl"));
        return connection;
    }
    public void delete(long id) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "DELETE FROM STUDENTS WHERE ID= "+id;
        getConnection().close();
        statement.execute(sql);
    }
    public ResultSet read(long id) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "SELECT FROM STUDENTS WHERE ID= "+id;
        statement.execute(sql);
        getConnection().close();
        return statement.getResultSet();
    }

    public void updateFirstname(long id, String firstname) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE  STUDENTS SET firstname = "+firstname+" WHERE id = " +id +")";
        statement.execute(sql);
    }
    public void updateLastname(long id, String lastname) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE  STUDENTS SET lastname = "+lastname+" WHERE id = " +id +")";
        statement.execute(sql);
    }
    public void updateAge(long id, int age) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE  STUDENTS SET age = "+age+" WHERE id = " +id +")";
        statement.execute(sql);
    }

    public void create(String lastname, String firstname) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "INSERT INTO STUDENTS (lastname, firstname) VALUES ("+ lastname+","+firstname+")";
        statement.execute(sql);

    }
    public void create(String lastname, String firstname, int age) throws IOException, SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "INSERT INTO STUDENTS (lastname, firstname, age) VALUES ("+ lastname+","+firstname+","+age+")";
        statement.execute(sql);

    }
}
