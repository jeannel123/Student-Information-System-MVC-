
package com.mycompany.jeannelmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InfoController {
    InformationView iv;
    
    public InfoController(InformationView temp){
        iv =temp;
        iv.allListeners(new AllAction());
    }
    class AllAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource() == iv.btnCreate){
                String studentName = iv.jTextField1.getText();
                String studentAge = iv.jTextField2.getText();
                String studentGender = iv.jTextField3.getText();
                String studentYear = iv.jTextField4.getText();
                String studentCourse = iv.jTextField5.getText();

                InfoModel imd = new InfoModel();
                imd.setStudentName(studentName);
                imd.setStudentAge(studentAge);
                imd.setStudentGender(studentGender);
                imd.setStudentYear(studentYear);
                imd.setStudentCourse(studentCourse);

                DAOInfoMVC.create(imd);
        } else if (e.getSource() == iv.btnUpdate){
            DAOInfoMVC.update(iv);
        } else if (e.getSource() == iv.searchBtn){
            DAOInfoMVC.search(iv);
        } else if (e.getSource() ==  iv.btnDelete){
            DAOInfoMVC.delete(iv);
        }
        else if (e.getSource() ==  iv.readBtn) {
            DAOInfoMVC.read(iv);
        }
            
    }
}
}