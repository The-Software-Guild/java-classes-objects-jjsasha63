package com.red;

import com.red.dao.DVDLibraryDAOI;
import com.red.dao.DVDLibraryDAO;
import com.red.entity.DVDLibrary;

import java.text.ParseException;
import java.util.Scanner;

/**
 * It's a controller class that handles the user's input and calls the appropriate methods from the DAO class
 */
public class AppController {

    private DVDLibraryDAOI dvdLibraryDAO;
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";


    public AppController() {
        this.dvdLibraryDAO = new DVDLibraryDAO();
    }

    public static void main(String[] args) throws ParseException {
        AppController appController = new AppController();
        appController.session();

    }

    private void session() throws ParseException {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            menu();
            info("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    dvdLibraryDAO.toFile();
                    break;
                case 2:
                    dvdLibraryDAO.fromFile();
                    break;
                case 3:
                    add(scanner);
                    break;
                case 4:
                    info("\n-Enter the DVD' id- ");
                    dvdLibraryDAO.deleteDVD(scanner.nextInt());
                    break;
                case 5:
                    edit(scanner);
                    break;
                case 6:
                    info("\n-Enter the DVD' title- ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    dvdLibraryDAO.showDVD(title);
                    break;
                case 7:
                    dvdLibraryDAO.showAll();
                    break;
                case 8:
                    dvdLibraryDAO.clear();
                    info("The library is empty now");
                    break;
                case 9:
                    choice = 0;
                    break;
                default:
                    warning("Wrong entry\nTry again");
                    break;
            }
            if (choice == 0) break;
        }
    }

    private void add(Scanner scanner){
        info("\nAdd new dvd\nEnter:\n-Title- ");
        scanner.nextLine();
        String title = scanner.nextLine();
        info("-Release date (dd/mm/yyyy)- ");
        String date = scanner.nextLine();
        info("-MPAA rating- ");
        Double MPAARating = scanner.nextDouble();
        info("-Director- ");
        scanner.nextLine();
        String director = scanner.nextLine();
        info("-Studio- ");
        String studio = scanner.nextLine();
        info("-User rating- ");
        Double userRating = scanner.nextDouble();
        dvdLibraryDAO.addDVD(title, date, MPAARating, director, studio, userRating);
    }

    private void edit(Scanner scanner){
        info("\nEdit a dvd\n-Enter DVD' id- ");
        int id = scanner.nextInt();
        DVDLibrary dvdLibrary = dvdLibraryDAO.findById(id);
        info("Enter new record or type 'keep' or 9999 for numeric fields to keep existing one:\n-Title- ");
        scanner.nextLine();
        String title = scanner.nextLine();
        if (title.equals("keep")) title = dvdLibrary.getTitle();
        info("-Release date (dd/mm/yyyy)- ");
        String date = scanner.nextLine();
        if (date.equals("keep")) date = dvdLibrary.getReleaseDate().toString();
        info("-MPAA rating- ");
        Double MPAARating = scanner.nextDouble();
        if (MPAARating == (double) 9999) MPAARating = dvdLibrary.getMPAARating();
        info("-Director- ");
        scanner.nextLine();
        String director = scanner.nextLine();
        if (director.equals("keep")) director = dvdLibrary.getDirectorName();
        info("-Studio- ");
        String studio = scanner.nextLine();
        if (studio.equals("keep")) studio = dvdLibrary.getStudio();
        info("-User rating- ");
        Double userRating = scanner.nextDouble();
        if (userRating == (double) 9999) userRating = dvdLibrary.getUserRating();
        dvdLibraryDAO.editDVD(id, title, date, MPAARating, director, studio, userRating);
    }

    /**
     * This function is used to display the menu options to the user
     */
    private static void menu() {
        info("\n-DVD Library-\n" + "-MENU-\n" +
                "1 - Save a DVD Library from a file\n" +
                "2 - Load a DVD Library to a file\n" +
                "3 - Add a DVD\n" +
                "4 - Delete DVD\n" +
                "5 - Update a DVD\n" +
                "6 - Show a DVD by ID\n" +
                "7 - Show the DVD Library\n" +
                "8 - Clean the library\n" +
                "9 - Close the session");
    }

    private static void info(String message){
        System.out.println(GREEN + message + RESET);
    }

    private static void warning(String message){
        System.out.println(RED + message + RESET);
    }

}
