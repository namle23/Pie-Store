package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateLogin {

    private Connection conn;

    public ValidateLogin(Connection conn) {
        this.conn = conn;
    }

    public boolean login(String username, String password) throws SQLException {

        boolean st = false;

        String sql = "SELECT * FROM USERS WHERE username=? AND password=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        st = rs.next();

        rs.close();

        return st;
    }

    public void create(String newusername, String newpassword,
            String newfullname, String newaddress, String newphone) throws SQLException {
        String sql = "INSERT INTO dbs.users (username, password, full_name, address, phone) VALUES (?,?,?,?,?);";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newusername);
        ps.setString(2, newpassword);
        ps.setString(3, newfullname);
        ps.setString(4, newaddress);
        ps.setString(5, newphone);

        ps.executeUpdate();

        ps.close();
    }

    public boolean exists(String username) throws SQLException {
        boolean st = false;

        String sql = "SELECT * FROM USERS WHERE username=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        st = rs.next();

        return st;
    }

//    public static boolean checkUser(String username, String password) {
//        boolean st = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs", "root", "root");
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE username=? AND password=?");
//
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            st = rs.next();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return st;
//    }
}
