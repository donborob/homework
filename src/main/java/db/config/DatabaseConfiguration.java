package db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * Created by boro on 17.03.15.
 */
@Configuration
public class DatabaseConfiguration {

    @Value("jdbc:derby:boro.db_dev;create=true")
    private String dbUrl;
    public String getDbUrl() {
        return dbUrl;
    }



    @Value("org.hibernate.dialect.DerbyTenSevenDialect")
    private String hibernateDialect;
    public  String getHibernateDialect() {
        return hibernateDialect;
    }
}