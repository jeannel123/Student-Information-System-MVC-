
package com.mycompany.jeannelmvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class dbConn {
    private static Connection conn;
    
    private static Connection createConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/infodb";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "@luCid19*#");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print(ex);
            JOptionPane.showMessageDialog(null, "Database Error!", "Error!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return conn;
    }
    
    public static Connection getConnection(){
        try {
            if (conn == null || conn.isClosed()){
                conn = createConnection();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error!", "Error!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
