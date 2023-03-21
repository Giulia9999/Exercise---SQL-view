import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection conn;
        Statement state;
        ResultSet res;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/newdb?" +
                    "user='root'&password=c620918fazuKl420");
            state = conn.createStatement();
            String queryItaly = "CREATE VIEW `italian_students` AS SELECT first_name, last_name FROM students WHERE country='Italy';";
            String queryGermany = "CREATE VIEW `german_students` AS SELECT first_name, last_name FROM students WHERE country='Germany';";

            String querySelectItaly = "SELECT * FROM `italian_students`";
            String querySelectGermany = "SELECT * FROM `german_students`";

            System.out.println("-----------Italian Students------------------");

            if(state.execute(querySelectItaly)){
                res = state.getResultSet();
                while (res.next()){
                    Student studentsItaly = new Student(res.getString("first_name"),res.getString("last_name"));
                    List<Student> italianStudents = new ArrayList<>();
                    italianStudents.add(studentsItaly);
                    System.out.println(italianStudents);
                }
            }
            System.out.println("-----------German Students------------------");
            if (state.execute(querySelectGermany)) {
                res = state.getResultSet();
                while (res.next()){
                    Student studentsGermany = new Student(res.getString("first_name"),res.getString("last_name"));
                    List<Student> germanStudents = new ArrayList<>();
                    germanStudents.add(studentsGermany);
                    System.out.println(germanStudents);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
