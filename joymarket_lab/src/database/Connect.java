package database;

import java.sql.*;

public class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "joymarket_db";
    private final String HOST = "localhost:3306";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private Connection con;
    private Statement st;
    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connect getInstance() {
        if (instance == null) return new Connect();
        return instance;
    }

    public ResultSet execQuery(String query) {
        try {
            st.executeQuery(query);
            return st.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void execUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}