import java.sql.*;

public class JdbcDemoApplication{

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int i;
        boolean b;
        StudentDB studentDB = new StudentDBWrapper();
        studentDB.studentDB();
        System.out.println("*********************************************************************");
        i = studentDB.insert("Chandan", 20, "CSE");
        if(i >= 1)
            System.out.println("\n***************************** Row Inserted ****************************\n");
        i = studentDB.insert("Anirudh", 17, "EEE");
        if(i >= 1)
            System.out.println("\n***************************** Row Inserted ****************************\n");
        i = studentDB.insert("Vinod", 19, "IT");
        if(i >= 1)
            System.out.println("\n***************************** Row Inserted ****************************\n");

        System.out.println("Database Is\n");
        studentDB.studentDB();

        System.out.println("Updating a Column:");
        b = studentDB.update(2, "course", "ECE");
        if(b)
            System.out.println("\n************************** Updated Database ****************************\n");
        studentDB.studentDB();
        System.out.println("Deleting a Record:");
        b = studentDB.delete(2);
        if(b)
            System.out.println("\n*************************** Updated Database ****************************\n");
        studentDB.studentDB();
        b = studentDB.update(3, "age", 20);
        if(b)
            System.out.println("\n*************************** Updated Database *****************************\n");
        studentDB.studentDB();
        System.out.println("Get Element By Id" + studentDB.get(1));
        System.out.println("Get Element By Name" + studentDB.get("Vinod"));
        System.out.println("\n********************** Delete All Records From Database *********************\n");
        studentDB.deleteAll();
        studentDB.studentDB();
        studentDB.close();

    }
}
