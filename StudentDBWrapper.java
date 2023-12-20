import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBWrapper implements StudentDB {
    private final DBUtil connection;
    private final Statement statement;

    public StudentDBWrapper() throws SQLException, ClassNotFoundException {
        connection = new DBUtil();
        statement = connection.createStatement();

    }

    @Override
    public int insert(String name, int age, String course) throws SQLException {
        String insert = "INSERT INTO studentDB(name, age, course) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connection.preparedStatement(insert);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setString(3, course);
        connection.commit();
        return preparedStatement.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String delete = "DELETE FROM studentDB where id = ?";
        PreparedStatement preparedStatement = connection.preparedStatement(delete);
        preparedStatement.setInt(1, id);
        connection.commit();
        int res = preparedStatement.executeUpdate();
        return res >= 0;
    }

    @Override
    public boolean update(int id, Object column, Object value) throws SQLException {
        String update = "UPDATE studentDB SET " + column + " = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.preparedStatement(update);
        preparedStatement.setObject(1, value);
        preparedStatement.setInt(2, id);
        int res = preparedStatement.executeUpdate();
        connection.commit();
        return res >= 0;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM studentDB");
        List<Student> student = new ArrayList<>();
        while (resultSet.next())
            student.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));

        return student;
    }

    @Override
    public Student get(String name) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM studentDB WHERE name = '" + name + "'");
        if (resultSet.next())
            return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
        return null;
    }

    @Override
    public Student get(int id) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from studentDB where id = '" + id + "'");
        if (resultSet.next())
            return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public void deleteAll() throws SQLException {
        this.deleteAllRecords();
    }

    private void deleteAllRecords() throws SQLException {
        connection.preparedStatement("TRUNCATE TABLE studentDB").execute();

    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public void studentDB() throws SQLException {
        List<Student> resultSet = this.getAll();
        System.out.println("-----------------------------------------------------------------------------\n" +
                this.pattern("id") + this.pattern("NAME") + this.pattern("AGE") + this.pattern("COURSE") + "|\n" +
                "-----------------------------------------------------------------------------");
        for (Student s : resultSet)
            System.out.println(this.pattern(s.getId()) + this.pattern(s.getName()) + this.pattern(s.getAge()) + this.pattern(s.getCourse()) + "|");
        System.out.println("-----------------------------------------------------------------------------");
    }

    private String pattern(Object item) {
        String ite = item.toString();
        if (ite.length() % 2 != 0)
            ite += " ";
        String repeat = " ".repeat(8 - (ite.length() - 1) / 2);
        return "|" + repeat + ite + repeat;
    }
}
