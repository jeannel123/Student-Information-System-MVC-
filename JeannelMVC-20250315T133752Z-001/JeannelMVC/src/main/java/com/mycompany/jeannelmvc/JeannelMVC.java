package com.mycompany.jeannelmvc;


public class JeannelMVC {

    public static void main(String[] args) {
        InformationView iv = new InformationView();
        iv.setVisible(true);
        new InfoController(iv);
    }
}
