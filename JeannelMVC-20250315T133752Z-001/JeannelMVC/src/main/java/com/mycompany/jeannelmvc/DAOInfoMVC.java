
package com.mycompany.jeannelmvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class DAOInfoMVC {
    public static void create(InfoModel imd){
        Connection conn = null;
        
        try {
            conn = dbConn.getConnection();
            String sql = "Insert into infotbl values (0,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, imd.getStudentName());
            pst.setString(2, imd.getStudentAge());
            pst.setString(3, imd.getStudentGender());
            pst.setString(4, imd.getStudentYear());
            pst.setString(5, imd.getStudentCourse());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Added!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOInfoMVC.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void update(InformationView iv){
        Connection conn = null;
        
        String ID = iv.txtID.getText();
        String Name = iv.jTextField1.getText();
        String Age = iv.jTextField2.getText();
        String Gender = iv.jTextField3.getText();
        String Year = iv.jTextField4.getText();
        String Course = iv.jTextField5.getText();
        
        try {
            conn = dbConn.getConnection();
            String sql = "UPDATE infotbl SET studentName = '"+Name+"', age = '"+Age+"', gender = '"+Gender+"', yearlvl = '"+Year+"', course = '"+Course+"' WHERE studentID ='"+ID+"'";
            Statement pst = conn.createStatement();
            pst.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Update successfully!");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOInfoMVC.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
    }
    
    public static ResultSet search(InformationView iv){
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dbConn.getConnection();
            String sql = "SELECT * from infotbl WHERE studentName = '"+iv.searchTxt.getText()+"'";
            Statement pst = conn.createStatement();
            rs = pst.executeQuery(sql);
            
          
            while(rs.next()){
                iv.txtID.setText(rs.getString("studentID"));
                iv.jTextField1.setText(rs.getString("studentName"));
                iv.jTextField2.setText(rs.getString("age"));
                iv.jTextField3.setText(rs.getString("gender"));
                iv.jTextField4.setText(rs.getString("yearlvl"));
                iv.jTextField5.setText(rs.getString("course"));
                
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOInfoMVC.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print(ex);
            JOptionPane.showMessageDialog(null, "Student not found!", "Not Available!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return rs;
    }
    
    public static void delete(InformationView iv){
        Connection conn = null;
        String Name = iv.jTextField1.getText();
        
        try {
            conn = dbConn.getConnection();
            String sql = "DELETE from infotbl WHERE studentName ='"+Name+"'";
            Statement pst = conn.createStatement();
            pst.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Delete successfully!");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOInfoMVC.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
    }
    
    public static ResultSet read(InformationView iv){
        ResultSet rs = null;
        Connection conn = null;
       
        
        try {
            conn = dbConn.getConnection();
            String sql = "SELECT * from infotbl";
            Statement pst = conn.createStatement();
            rs = pst.executeQuery(sql);
            
            DefaultTableModel model = (DefaultTableModel) iv.jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                String  ID = rs.getString("studentID");
                String  Name  = rs.getString("studentName");
                String Age = rs.getString("age");
                String Gender = rs.getString("gender");
                String Year = rs.getNString("yearlvl");
                String Course = rs.getNString("course");
                
                Object[] row = {ID, Name, Age, Gender, Year, Course};
                model.addRow(row);
            }
            
            TableColumn column = iv.jTable1.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
        } catch (SQLException ex) {
            Logger.getLogger(DAOInfoMVC.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
}
