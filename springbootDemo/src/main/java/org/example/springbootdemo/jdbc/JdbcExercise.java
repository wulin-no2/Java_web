package org.example.springbootdemo.jdbc;
import java.sql.*;
public class JdbcExercise {
    static final String db_url= "jdbc:mysql://localhost/MY_DB";
    static final String user = "root";
    static final String pass = "wulin9786";
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(db_url, user, pass);
        Statement st = conn.createStatement();
//        st.executeUpdate("insert into Student(name, marks) values ('Karl', 90)");
        ResultSet rs = st.executeQuery(
                "select * from Student");
//            "SELECT name, marks FROM Student WHERE marks >= 60");
        while (rs.next()) {
            System.out.print(rs.getString("name"));
            System.out.print(" ");
            System.out.println(rs.getInt("marks"));
        }
    }
}
