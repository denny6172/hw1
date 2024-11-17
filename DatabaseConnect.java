/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class DatabaseConnect {

    private static final String DB_RENTAL = "rentalRecord";
    private static final String DB_MEMBER = "memberInfo";

    private static final String DB_URL = "jdbc:sqlserver://140.136.149.167\\MSSQLSERVER_2022:1433;encrypt=true;trustServerCertificate=true;DatabaseName=member";
    private static final String DB_UID = "sa";
    private static final String DB_PWD = "alan0819";

    public DatabaseConnect() {
        //System.out.println("DatabaseConnect");
        createMemberInfoTable();
        createRentalRecordTable();
    }

    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet re;

        createMemberInfoTable();
        createRentalRecordTable();

        String sql = "select * from memberInfo";
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            st = con.createStatement();
            re = st.executeQuery(sql);
            System.out.println(re);
            //System.out.printf("%-20s %-10s %-15s %-10s\n", "Name", "Phone", "Password", "CardNumber");
            System.out.println("性名                  電話       密碼             卡號");
            System.out.println("==================== ========== =============== ==========");
            while(re.next()) {
                String mr = re.getString(1);
                String rd = re.getString(2);
                String rl = re.getString(3);
                String td = re.getString(4);
                //String tl= re.getString(5);
                System.out.println(mr+" "+rd+" "+rl+" "+td+" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }

        //dumpTable(DB_MEMBER);
        //dumpTable(DB_RENTAL);

//        insertRental("12345678", 1, "中和");
//        dumpTable(DB_RENTAL);
//        updateReturn( "12345678", 4, "輔仁");
//        dumpTable(DB_RENTAL);


        //removeDBTable(DB_MEMBER);
        removeDBTable(DB_RENTAL);
        //createMemberInfoTable();
        //createRentalRecordTable();

        /*
        insertRental("12345678", 1, "中和");
        dumpTable(DB_RENTAL);
        System.out.println("11");
        insertRental("77889900", 9, "XX");
        dumpTable(DB_RENTAL);
        System.out.println("22");
        insertReturn( "12345678", 4, "輔仁");
        dumpTable(DB_RENTAL);
        System.out.println("33");
        insertRental("98765432", 999, "OO");
        dumpTable(DB_RENTAL);
        System.out.println("44");

         */
    }

    public void dumpDBTable() {
        dumpTable(DB_MEMBER);
        dumpTable(DB_RENTAL);
    }

    private static void removeDBTable(String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            dropTable(conn, tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTable(Connection conn, String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table " + tableName + " has been dropped");
        } catch (SQLException e) {
            System.out.println("Table " + tableName + " could not be dropped");
            throw new RuntimeException(e);
        }
    }

    private static void createMemberInfoTable() {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, DB_MEMBER, null);

            if (!rs.next()) {   // 表示不存在
                String createTableQuery = "CREATE TABLE " + DB_MEMBER + " (" +
                        "name VARCHAR(20) NOT NULL PRIMARY KEY, " +
                        "phonenumber VARCHAR(15) NOT NULL DEFAULT 1, " +
                        "password VARCHAR(15) NOT NULL, " +
                        "cardnumber VARCHAR(15) NOT NULL)";
                try (Statement st = conn.createStatement()) {
                    Statement statement = conn.createStatement();
                    statement.executeUpdate(createTableQuery);
                    //System.out.println("Table " + DB_MEMBER + " create successful");
                }
            }
            else {
                //System.out.println("Table " + DB_MEMBER + " is exist");
            }
        } catch (SQLException e) {
            System.out.println("fail check/create '" + DB_MEMBER + "' table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void createRentalRecordTable() {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, DB_RENTAL, null);

            if (!rs.next()) {   // 表示不存在
                String createTableQuery = "CREATE TABLE " + DB_RENTAL + " (" +
                        "cardNumber VARCHAR(10) NOT NULL, " +
                        "inLease BIT NOT NULL DEFAULT 1, " +
                        "rentalDate DATE NOT NULL, " +
                        "rentalStationNum INT NOT NULL, " +
                        "rentalStationPos VARCHAR(20) NOT NULL , " +
                        "returnDate DATE, " +
                        "returnStationNum INT, " +
                        "returnStationPos VARCHAR(20))";
                try (Statement st = conn.createStatement()) {
                    Statement statement = conn.createStatement();
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Table " + DB_RENTAL + " create successful");
                }
            }
            else {
                //System.out.println("Table " + DB_RENTAL + " is exist");
            }
        } catch (SQLException e) {
            System.out.println("fail check/create '"+ DB_RENTAL +"' table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean checkMember(String cardNumber) {
        Connection con;
        Statement st;
        ResultSet re;
        String sql = "select count(cardnumber) from memberRecord where cardnumber='" + cardNumber + "'";
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            st = con.createStatement();
            re = st.executeQuery(sql);
            re.next();
            int count = re.getInt(1);
            if (count > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
            return false;
        }

        return false;
    }

    public boolean checkInfo(String cardnumber) {
        Connection con;
        Statement st;
        ResultSet re;
        String sql = "select count(cardnumber) from memberInfo where cardnumber='" + cardnumber + "'";
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            st = con.createStatement();
            re = st.executeQuery(sql);
            re.next();
            int count = re.getInt(1);
            if (count != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
            return false;
        }

        return false;
    }

    public boolean checkRentState(String cardNumber) {
        Connection con;
        Statement st;
        ResultSet rs;
        String sql = "SELECT * FROM " + DB_RENTAL + " WHERE cardNumber = '" + cardNumber + "' AND inLease = 1";
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }

            //rs.next();
            //int count = rs.getInt(1);
            //if (count != 0)
            //    return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.println("fail");
            return false;
        }

        return false;
    }

    public void insertInfo(String name, String phonenumber, String password, String cardnumber ) {
        Connection con;
        String sql = "insert into memberInfo values('" + name + "','" + phonenumber + "','" + password + "','" + cardnumber + "')";
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }

    public static void insertRental(String cardNumber, int stationNum, String stationPos) {
        //Timestamp now = new Timestamp(System.currentTimeMillis());
        Date now = new java.sql.Date(System.currentTimeMillis());
        String insQuery = "INSERT INTO " + DB_RENTAL + " (cardNumber, rentalDate, rentalStationNum, rentalStationPos) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            PreparedStatement pstmt = conn.prepareStatement(insQuery);
            pstmt.setString(1, cardNumber);
            pstmt.setDate(2, now);
            pstmt.setInt(3, stationNum);
            pstmt.setString(4, stationPos);

            pstmt.executeUpdate();
            //System.out.println("Table " + DB_RENTAL + " inserted successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateReturn(String cardNumber, int stationNum, String stationPos) {
        //Timestamp now = new Timestamp(System.currentTimeMillis());
        Date now = new Date(System.currentTimeMillis());
        String selQuery = "SELECT * FROM " + DB_RENTAL +
                        " WHERE cardNumber = '" + cardNumber + "' AND inLease = 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(selQuery)) {
            //
            while (rset.next()) {
                //System.out.println("cardNumber = " + rset.getString("cardNumber"));

                String updateQuery = "UPDATE " + DB_RENTAL +
                                    " SET returnDate = ?,  returnStationNum = ?, returnStationPos = ?, inLease = 0" +
                                    " WHERE cardNumber = '" + cardNumber + "' AND inLease = 1";

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    //
                    pstmt.setDate(1, now);
                    pstmt.setInt(2, stationNum);
                    pstmt.setString(3, stationPos);
                    //
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        //System.out.println("Table rentalRecord update successfully " + cardNumber);
                    }
                    else {
                        //System.out.println("Table rentalRecord is empty " + cardNumber);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void dumpTable(String tableName) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM " + tableName;
            ResultSet rset = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rset.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuffer sqlQuery = new StringBuffer("INSERT INTO " + tableName + " (");
            for (int i = 1; i <= columnCount; i++) {
                //sqlQuery.append(metaData.getColumnClassName(i));
                sqlQuery.append(metaData.getColumnName(i));
                if (i < columnCount)
                    sqlQuery.append(", ");
            }
            sqlQuery.append(") VALUES ");

            boolean first = true;
            while (rset.next()) {
                //if (!first) {
                //    sqlQuery.append(", ");
                //}
                sqlQuery.append("(");

                for (int i = 1; i <= columnCount; i++) {
                    String value = rset.getString(i);
                    if (value == null) {
                        sqlQuery.append("NULL");
                    } else {
                        sqlQuery.append("'").append(value.replace("'", "''")).append("'");
                    }

                    if (i < columnCount)
                        sqlQuery.append(", ");
                }

                sqlQuery.append(")");

                System.out.println(sqlQuery.toString());


                sqlQuery.setLength(0);
                sqlQuery.append("INSERT INTO " + tableName + " (");
                for (int i = 1; i <= columnCount; i++) {
                    sqlQuery.append(metaData.getColumnName(i));
                    if (i < columnCount)
                        sqlQuery.append(", ");
                }
                sqlQuery.append(") VALUES ");
                first = false;
            }
        } catch (Exception e) {
            System.out.println("轟");
            throw new RuntimeException(e);
        }
    }

    public void showHistory(String cardNumber, int qint) {
        Connection con;
        Statement st;
        ResultSet re;
        if (qint == 1) {
            String sql = "select * from memberRecord where cardnumber='" + cardNumber + "' and datediff(d,rentaltime,getdate())<7";
            try {
                con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
                st = con.createStatement();
                re = st.executeQuery(sql);
                while (re.next()) {
                    String mr = re.getString(1);
                    String rd = re.getString(2);
                    String rl = re.getString(3);
                    String td = re.getString(4);
                    String tl = re.getString(5);
                    System.out.println("卡號用戶:" + mr + " 租借:" + rd + " " + rl + " 歸還:" + td + " " + tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
        if (qint == 2) {
            String sql = "select * from memberRecord where cardnumber='" + cardNumber + "' and datediff(d,rentaltime,getdate())<30";
            try {
                con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
                st = con.createStatement();
                re = st.executeQuery(sql);
                while (re.next()) {
                    String mr = re.getString(1);
                    String rd = re.getString(2);
                    String rl = re.getString(3);
                    String td = re.getString(4);
                    String tl = re.getString(5);
                    System.out.println("卡號用戶:" + mr + " 租借:" + rd + " " + rl + " 歸還:" + td + " " + tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
        if (qint == 3) {
            String sql = "select * from memberRecord where cardnumber='" + cardNumber + "' and datediff(d,rentaltime,getdate())<180";
            try {
                con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
                st = con.createStatement();
                re = st.executeQuery(sql);
                while (re.next()) {
                    String mr = re.getString(1);
                    String rd = re.getString(2);
                    String rl = re.getString(3);
                    String td = re.getString(4);
                    String tl = re.getString(5);
                    System.out.println("卡號用戶:" + mr + " 租借:" + rd + " " + rl + " 歸還:" + td + " " + tl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        }
    }
}
