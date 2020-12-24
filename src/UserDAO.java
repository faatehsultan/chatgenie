/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.genie.messenger;

import java.awt.Font;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author faateh
 */
public class UserDAO {

    Connection con;
    Statement stat;
    ResultSet rs;

    public UserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_genie", "root", "");
            System.out.println("user database connected!");
            stat = con.createStatement();
        } catch (Exception ex) {
            System.out.println("User Database Connection failure!");
            createDatabaseFailureDialog(); //will sleep for 3 seconds
            System.exit(1);
        }
    }

    public void addUser(String username, String password) {
        //insert data to database (expecting only valid data)
        try {
            stat.executeUpdate("INSERT INTO users VALUES ('" + username + "', '" + password + "', DEFAULT);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isUserPresent(String username) {
        try {
            rs = stat.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //never execute
    }

    public boolean verifyCredentials(String username, String password) {
        //expecting only valid data
        try {
            rs = stat.executeQuery("SELECT password FROM users WHERE username = '" + username + "';");
            rs.next();
            return password.equals(filterStringFromDatabase(rs.getString("password")));
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //never execute
    }

    private String filterStringFromDatabase(String src) {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            if ((int) src.charAt(i) != 0) {
                result += src.charAt(i);
            }
        }
        return result;
    }

    //database failure dialog
    private void createDatabaseFailureDialog() {
        JFrame frame = new JFrame("Chat Genie 1.0 - ERROR");
        frame.setIconImage(new ImageIcon("favicon.png").getImage());
        frame.setSize(500, 150);
        frame.setLocationRelativeTo(null);
        JLabel msg = new JLabel("ERROR: Connection to Database Failed!",SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(msg);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
