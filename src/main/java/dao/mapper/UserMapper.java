package dao.mapper;

import model.User;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by boro on 27.03.15.
 */
public class UserMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(i+1);
        user.setFirstname(resultSet.getString("FIRSTNAME"));
        user.setLastname(resultSet.getString("LASTNAME"));
        user.setAge(resultSet.getInt("AGE"));
        return user;
    }
}
