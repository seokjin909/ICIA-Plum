package com.main;


import com.controller.DataController;
import com.dao.MusicDao;

public class MainClass {
    public static void main(String[] args) {
        DataController cc = new DataController();
        cc.run();
    }
}
