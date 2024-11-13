import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class DatabaseConnect {
    public static void main(String[] args) {
        String dbURL = "jdbc:sqlserver://140.136.149.167\\MSSQLSERVER_2022:1433;encrypt=true;trustServerCertificate=true;DatabaseName=member";
        String userName="sa";
        String userPwd="alan0819";
        String sql="select * from memberRecord";

        Connection con;
        Statement st;
        ResultSet re;
        try {
            con=DriverManager.getConnection(dbURL,userName,userPwd);
            st= con.createStatement();
            re=st.executeQuery(sql);
            System.out.println(re);
            while(re.next()){
                String mr= re.getString(1);
                String rd= re.getString(2);
                String rl= re.getString(3);
                String td= re.getString(4);
                String tl= re.getString(5);
                System.out.println(mr+" "+rd+" "+rl+" "+td+" "+tl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }


    }
    public Boolean checkmember(String cardnumber){
        String dbURL = "jdbc:sqlserver://140.136.149.167\\MSSQLSERVER_2022:1433;encrypt=true;trustServerCertificate=true;DatabaseName=member";
        String userName="sa";
        String userPwd="alan0819";
        String sql="select count(phonenumber) from memberRecord where phonenumber='"+cardnumber+"'";
        Connection con;
        Statement st;
        ResultSet re;
        try {
            con=DriverManager.getConnection(dbURL,userName,userPwd);
            st= con.createStatement();
            re=st.executeQuery(sql);
            re.next();
            int count=re.getInt(1);
            if(count>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
            return false;
        }
        return false;
    }

    public void showhistory(String cardnumber,int qint){
        String dbURL = "jdbc:sqlserver://140.136.149.167\\MSSQLSERVER_2022:1433;encrypt=true;trustServerCertificate=true;DatabaseName=member";
        String userName="sa";
        String userPwd="alan0819";
        Connection con;
        Statement st;
        ResultSet re;
        if(qint==1){
            String sql="select * from memberRecord where phonenumber='"+cardnumber+"' and datediff(d,rentaltime,getdate())<7";
            try {
                con=DriverManager.getConnection(dbURL,userName,userPwd);
                st= con.createStatement();
                re=st.executeQuery(sql);
                while(re.next()){
                    String mr= re.getString(1);
                    String rd= re.getString(2);
                    String rl= re.getString(3);
                    String td= re.getString(4);
                    String tl= re.getString(5);
                    System.out.println(mr+" "+rd+" "+rl+" "+td+" "+tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
        if(qint==2){
            String sql="select * from memberRecord where phonenumber='"+cardnumber+"' and datediff(d,rentaltime,getdate())<30";
            try {
                con=DriverManager.getConnection(dbURL,userName,userPwd);
                st= con.createStatement();
                re=st.executeQuery(sql);
                while(re.next()){
                    String mr= re.getString(1);
                    String rd= re.getString(2);
                    String rl= re.getString(3);
                    String td= re.getString(4);
                    String tl= re.getString(5);
                    System.out.println(mr+" "+rd+" "+rl+" "+td+" "+tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
        if(qint==3){
            String sql="select * from memberRecord where phonenumber='"+cardnumber+"' and datediff(d,rentaltime,getdate())<180";
            try {
                con=DriverManager.getConnection(dbURL,userName,userPwd);
                st= con.createStatement();
                re=st.executeQuery(sql);
                while(re.next()){
                    String mr= re.getString(1);
                    String rd= re.getString(2);
                    String rl= re.getString(3);
                    String td= re.getString(4);
                    String tl= re.getString(5);
                    System.out.println(mr+" "+rd+" "+rl+" "+td+" "+tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
    }
}
