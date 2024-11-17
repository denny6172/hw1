/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;


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

        String sql = "select * from " + DB_MEMBER;
        try {
            con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            st = con.createStatement();
            re = st.executeQuery(sql);
            System.out.println(re);
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


        //showRentHistory("9999999999", 1);
        dumpDBTable();

//        insertRental("12345678", 1, "中和");
//        dumpDBTable(DB_RENTAL);
//        updateReturn( "12345678", 4, "輔仁");
//        dumpDBTable(DB_RENTAL);


//        removeDBTable(DB_MEMBER);
//        removeDBTable(DB_RENTAL);
//        createMemberInfoTable();
//        createRentalRecordTable();
    }

    /**
     *
     */
    public static void dumpDBTable() {
        dumpDBTable(DB_MEMBER);
        dumpDBTable(DB_RENTAL);
    }

    /**
     *
     * @param tableName
     */
    private static void removeDBTable(String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            dropTable(conn, tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param conn
     * @param tableName
     */
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

    /**
     *
     */
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

    /**
     *
     */
    private static void createRentalRecordTable() {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, DB_RENTAL, null);

            if (!rs.next()) {   // 表示不存在
                String createTableQuery = "CREATE TABLE " + DB_RENTAL + " (" +
                        "cardNumber VARCHAR(10) NOT NULL, " +
                        "inLease BIT NOT NULL DEFAULT 1, " +
                        "rentalTime VARCHAR(20) NOT NULL, " +
                        "rentalStationNum INT NOT NULL, " +
                        "rentalStationPos VARCHAR(20) NOT NULL , " +
                        "returnTime VARCHAR(20), " +
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

    /**
     *
     * @param cardNumber
     * @return
     */
    public boolean checkMember(String cardNumber) {
        String sql = "SELECT COUNT(cardNumber) FROM " + DB_RENTAL + " WHERE cardNumber='" + cardNumber + "'";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rsts = stmt.executeQuery(sql);
            rsts.next();
            int count = rsts.getInt(1);
            if (count > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
            return false;
        }

        return false;
    }

    /*

     */
    public boolean checkInfo(String cardnumber) {
        String sql = "SELECT COUNT(cardnumber) FROM " + DB_MEMBER + " WHERE cardnumber='" + cardnumber + "'";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rsts = stmt.executeQuery(sql);
            rsts.next();
            int count = rsts.getInt(1);
            if (count != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
            return false;
        }

        return false;
    }

    /**
     *
     * @param name
     * @param phonenumber
     * @param password
     * @param cardnumber
     */
    public void insertInfo(String name, String phonenumber, String password, String cardnumber ) {
        String sql = "INSERT INTO " + DB_MEMBER + " VALUES('" + name + "','" + phonenumber + "','" + password + "','" + cardnumber + "')";
        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }

    public static boolean removeMember(String account, String password) {
        boolean result = false;
        String selQuery = "SELECT * FROM " + DB_MEMBER +
                " WHERE name = '" + account + "'";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
             Statement stmt = conn.createStatement();
             ResultSet rsts = stmt.executeQuery(selQuery)) {
            if (rsts.next() && rsts.getString("password").strip().equals(password)) {
                String sql = "DELETE FROM " + DB_MEMBER + " WHERE name ='" + account + "'";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.executeUpdate();
                    result = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void insertMember(String account, String phoneNumber, String password) {
        //Timestamp now = new Timestamp(System.currentTimeMillis());
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String insQuery = "INSERT INTO " + DB_MEMBER +
                " (name, phonenumber, password)" +
                " VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            PreparedStatement pstmt = conn.prepareStatement(insQuery);
            pstmt.setString(1, account);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, password);

            pstmt.executeUpdate();
            //System.out.println("Table " + DB_RENTAL + " inserted successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCardNum(String account, String cardNumber) {
        //Timestamp now = new Timestamp(System.currentTimeMillis());
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String selQuery = "SELECT * FROM " + DB_MEMBER +
                " WHERE name = '" + account + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
             Statement stmt = conn.createStatement();
             ResultSet rsts = stmt.executeQuery(selQuery)) {
            //
            while (rsts.next()) {
                System.out.println("cardNumber = " + rsts.getString("cardNumber"));

                String updateQuery = "UPDATE " + DB_MEMBER +
                        " SET cardnumber = ?" +
                        " WHERE name = '" + account + "'";

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    //
                    pstmt.setString(1, cardNumber);
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

    /**
     *
     * @param cardNumber
     * @return
     */
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

    /**
     *
     * @param cardNumber
     * @param stationNum
     * @param stationPos
     */
    public static void insertRental(String cardNumber, int stationNum, String stationPos) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = new java.sql.Date(System.currentTimeMillis());
        String insQuery = "INSERT INTO " + DB_RENTAL +
                        " (cardNumber, rentalTime, rentalStationNum, rentalStationPos)" +
                        " VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD)) {
            PreparedStatement pstmt = conn.prepareStatement(insQuery);
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, sdf.format(now));
            pstmt.setInt(3, stationNum);
            pstmt.setString(4, stationPos);

            pstmt.executeUpdate();
            //System.out.println("Table " + DB_RENTAL + " inserted successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param cardNumber
     * @param stationNum
     * @param stationPos
     */
    public static void updateReturn(String cardNumber, int stationNum, String stationPos) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date now = new Date(System.currentTimeMillis());
        String selQuery = "SELECT * FROM " + DB_RENTAL +
                        " WHERE cardNumber = '" + cardNumber + "' AND inLease = 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rsts = stmt.executeQuery(selQuery)) {
            //
            while (rsts.next()) {
                //System.out.println("cardNumber = " + rsts.getString("cardNumber"));

                String updateQuery = "UPDATE " + DB_RENTAL +
                                    " SET returnTime = ?,  returnStationNum = ?, returnStationPos = ?, inLease = 0" +
                                    " WHERE cardNumber = '" + cardNumber + "' AND inLease = 1";

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    //
                    pstmt.setString(1, sdf.format(now));
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

    /**
     *
     * @param tableName
     */
    public static void dumpDBTable(String tableName) {
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
             Statement stmt = conn.createStatement();
             ResultSet rsts = stmt.executeQuery(sql)) {

             ResultSetMetaData metaData = rsts.getMetaData();
             int columnCount = metaData.getColumnCount();

             StringBuffer sqlQuery = new StringBuffer("INSERT INTO " + tableName + " (");
             for (int i = 1; i <= columnCount; i++) {
                 //sqlQuery.append(metaData.getColumnClassName(i));
                 sqlQuery.append(metaData.getColumnName(i));
                 if (i < columnCount)
                     sqlQuery.append(", ");
             }
             sqlQuery.append(") VALUES ");
             System.out.println(sqlQuery.toString());

             while (rsts.next()) {
                 sqlQuery.setLength(0);
                 sqlQuery.append("(");

                 for (int i = 1; i <= columnCount; i++) {
                     String value = rsts.getString(i);
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
             }
        } catch (Exception e) {
            //System.out.println("轟");
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param cardNumber
     * @param qint
     */
    public static void showRentHistory(String cardNumber, int qint) {
        String member = null;
        String query = "SELECT * FROM " + DB_MEMBER + " WHERE cardNumber = '" + cardNumber + "'";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rsts = stmt.executeQuery(query);
            if (rsts.next()) {
                member = rsts.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int days = 0;

        if (qint == 1)
            days = 7;
        else if (qint == 2)
            days = 30;
        else if (qint == 3)
            days = 180;


        query = "SELECT * FROM " + DB_RENTAL + " WHERE cardNumber = '" + cardNumber + "'";
        if (days > 0)
            query += " AND datediff(d,rentalTime,getdate())<" + days;
        //System.out.println(query);



        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rsts = stmt.executeQuery(query);
            while (rsts.next()) {
                String cardNum = rsts.getString(1);       // cardNumber
                String rentTime = rsts.getString(3);      // rentalTime
                String rentStaNum = rsts.getString(4);    // rentalStationNum
                String rentStaPos = rsts.getString(5);    // rentalStationPos
                String retTime = rsts.getString(6);       // returnTime
                String retStaNum = rsts.getString(7);     // returnStationNum
                String retStaPos = rsts.getString(8);     // returnStationPos
                System.out.println("帳號：" + member + ", 卡號：" + cardNum + ",  租借：" + rentTime + " / " + rentStaNum + " / " + rentStaPos + ",  歸還：" + retTime + " / " + retStaNum + " / " + retStaPos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }
}
