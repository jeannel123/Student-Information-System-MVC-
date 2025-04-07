
package com.mycompany.jeannelmvc;



import java.sql.*;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DAOInfoMVCTest {
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    

    /**
     * Test of create method, of class DAOInfoMVC.
     */
    @Test
    public void testCreate() throws SQLException {
        InfoModel imd = new InfoModel();
                imd.setStudentName("Frezzy Maeve Bacasejos");
                imd.setStudentAge("20");
                imd.setStudentGender("Female");
                imd.setStudentYear("3");
                imd.setStudentCourse("BSIT");
                
      boolean studentExists = checkStudentExists(imd.getStudentName());
      assertTrue(studentExists);
      
      DAOInfoMVC.create(imd);
    }
    
    private boolean checkStudentExists(String StudentName) throws SQLException {
        Connection conn = dbConn.getConnection();
        String sql = "SELECT * FROM infotbl WHERE studentName = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "juan dela cruz");
        ResultSet rs = pst.executeQuery();
        return rs.next();
        
    }

    /**
     * Test of update method, of class DAOInfoMVC.
     */
    @Test
    public void testUpdate() throws SQLException {
        InformationView iv = new InformationView();
        
        iv.jTextField1.setText("master yi");
        iv.jTextField2.setText("25");
        iv.jTextField3.setText("male");
        iv.jTextField4.setText("4");
        iv.jTextField5.setText("beed");
        
        boolean bookExists = checkStudentExists(iv.getName());
        assertTrue(bookExists);
        
        DAOInfoMVC.update(iv);
    }

    /**
     * Test of search method, of class DAOInfoMVC.
     */
    @Test
    public void testSearch() {
        InformationView iv = new InformationView();
        iv.searchTxt.setText("juan dela cruz");
        rs = DAOInfoMVC.search(iv);
        
        if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
        assertTrue(rs != null, "not null Connection access");
    }

    /**
     * Test of delete method, of class DAOInfoMVC.
     */
    @Test
    public void testDelete() throws SQLException {
        InformationView iv = new InformationView();
        iv.setTitle("fiora");
       
        boolean bookExists = checkStudentExists(iv.getTitle());
        assertTrue(bookExists);
        
        DAOInfoMVC.delete(iv);
    }

    /**
     * Test of read method, of class DAOInfoMVC.
     */
    @Test
    public void testRead() {
       InformationView iv = new InformationView();
        
        rs = DAOInfoMVC.read(iv);
        
        if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
        assertTrue(rs !=null, "not null Connection access");

    }

    
    
}
