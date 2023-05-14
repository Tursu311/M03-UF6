import java.sql.SQLException;
import java.util.List;

public interface DAODB<T> {
    // CRUD
    boolean create(T t) throws SQLException;
    boolean read(T t);
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    // ALTRES
    Candidat exists(int id) throws SQLException;
    int count();
    List<T> all() throws SQLException;
}