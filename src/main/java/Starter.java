import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Starter {
    public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Properties p = new Properties();
        ClassLoader classLoader = Starter.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("derby.properties");
        p.load(inputStream);
        Connection connection = DriverManager.getConnection(p.getProperty("connectionUrl"));
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE STUDENTS ("
                + "id INTEGER not NULL,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "age INTEGER,"
                + "PRIMARY KEY(id))";
        statement.execute(sql);
        String sql1 = "CREATE TABLE ORDERS ("
                + "id INTEGER not NULL,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "totalAmount INTEGER,"
                + "PRIMARY KEY(id))";
        statement.execute(sql1);

        sql = "INSERT INTO STUDENTS VALUES ("
                + "1,"
                + "'Ruzal',"
                + "'Sitdikov',"
                + "19)";
        statement.executeUpdate(sql);

        sql1 = "INSERT INTO ORDERS VALUES ("
                + "2,"
                + "'Gleb',"
                + "'Syomshchikov',"
                + "12000)";
        statement.executeUpdate(sql1);

        sql = "SELECT STUDENTS.lastname, ORDERS.totalAmount FROM ORDERS JOIN STUDENTS ON (ORDERS.totalAmount>1000)";
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.println(result.getString(1));
        }
    }
}
