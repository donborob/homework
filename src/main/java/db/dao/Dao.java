package db.dao;

import db.entity.Model;
import db.entity.Student;

/**
 * Created by boro on 17.03.15.
 */
public interface Dao<T extends Model> {
    void addOrUpdate(T t);

    void delete(int id);

    T get(long id);
}
