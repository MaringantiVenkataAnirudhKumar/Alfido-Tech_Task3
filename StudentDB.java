import java.sql.SQLException;
import java.util.List;

public interface StudentDB {
    int insert(String name, int age, String course) throws SQLException;

    boolean delete(int id) throws SQLException;

    boolean update(int id, Object column, Object value) throws SQLException;

    List<Student> getAll() throws SQLException;
    Student get(int id) throws SQLException;

    Student get(String name) throws SQLException;

    void close() throws SQLException;
    void deleteAll() throws SQLException;

    void studentDB() throws SQLException;
}
