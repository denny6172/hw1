import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class DatabaseConnect {
    public static void main(String[] args) {
        String dbURL = "jdbc:sqlserver://DESKTOP-D6AAT35\\MSSQLSERVER_2022:1433;encrypt=true;trustServerCertificate=true;DatabaseName=member";
        String userName="sa";
        String userPwd="alan0819";
        String sql="select * from member";

        Connection con;
        Statement st;
        ResultSet re;
        try {
            con=DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("yyy");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }
}
