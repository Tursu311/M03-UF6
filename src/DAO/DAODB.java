package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAODB<T> {
    // CRUD
    boolean create(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    // ALTRES
    T exists(int id) throws SQLException;
    List<T> all() throws SQLException;
}