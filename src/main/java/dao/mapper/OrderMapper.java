package dao.mapper;

import model.Order;
import model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by boro on 31.03.15.
 */
public class OrderMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(i + 1);
        order.setCustomerId(resultSet.getInt("CUSTOMERID"));
        order.setSellerId(resultSet.getInt("SELLERID"));
        order.setTotalAmount(resultSet.getInt("TOTALAMOUNT"));
        return order;
    }
}
