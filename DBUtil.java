import java.sql.*;

public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "jdbc:mysql://localhost:3306/student";
    private static final String USERNAME = "acr123";
    private static final String PASSWORD = "acr123";
    private final Connection connection;

    public DBUtil() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        this.connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
    }
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    public void close() throws SQLException {
        connection.close();
    }

    public void commit() throws SQLException{
        connection.commit();
    }
}
