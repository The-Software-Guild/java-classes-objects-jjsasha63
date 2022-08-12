package com.red;

import com.red.dao.DVDLibraryDAO;

import java.util.Scanner;
import java.util.logging.Logger;

public class AppController {

    private static Logger logger = Logger.getLogger(AppController.class.getName());
    private DVDLibraryDAO dvdLibraryDAO;

    public AppController(){dvdLibraryDAO = new DVDLibraryDAO();}

    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.session(appController.dvdLibraryDAO);
    }


    private void session(DVDLibraryDAO dvdLibraryDAO){
        Scanner scanner = new Scanner(System.in);
        menu();



    }

    private static void menu(){
        logger.info("DVD Library\n" + "MENU\n" +
                "1 - Load DVD Library from a file\n" +
                "2 - Save DVD Library to a file\n" +
                "3 - Add DVD\n" +
                "4 - Delete DVD\n" +
                "5 - Update DVD\n" +
                "6 - Show particular DVD\n" +
                "7 - Show DVD Library");
    }

    private static void choice(int choice){
//        switch (choice){
//            case 1:
//
//        }
    }
}
