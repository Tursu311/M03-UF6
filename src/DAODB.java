import java.sql.SQLException;
import java.util.List;

public interface DAODB<T> {
    // CRUD
    boolean create(T t) throws SQLException;
    boolean read(T t); // El read no l'utilitzo ja que ja es llegeix i es mostra amb el List all i el exists preguntar com implementar ho be
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    // ALTRES
    T exists(int id) throws SQLException;
    int count();
    List<T> all() throws SQLException;
}