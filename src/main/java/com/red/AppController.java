package com.red;

import com.red.dao.DVDLibraryDAO;
import com.red.entity.DVDLibrary;

import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * It's a controller class that handles the user's input and calls the appropriate methods from the DAO class
 */
public class AppController {

    private static Logger logger = Logger.getLogger(AppController.class.getName());
    private DVDLibraryDAO dvdLibraryDAO;

    public AppController(){dvdLibraryDAO = new DVDLibraryDAO(logger);}

    public static void main(String[] args) throws ParseException {
        AppController appController = new AppController();
        appController.session(appController.dvdLibraryDAO);

    }


    /**
     * It's a recursive function that displays a menu, takes user's choice, performs the corresponding action and calls
     * itself again
     *
     * @param dvdLibraryDAO the DAO object that will be used to perform the CRUD operations.
     */
    private void session(DVDLibraryDAO dvdLibraryDAO) throws ParseException {
        int id;
        String title,date,director,studio;
        Double MPAARating, userRating;
        Scanner scanner = new Scanner(System.in);
        menu();
        logger.info("Your choice: ");
        int choice  = scanner.nextInt();

        switch (choice){
            case 1:
                dvdLibraryDAO.toFile();
                break;
            case 2:
                dvdLibraryDAO.fromFile();
                break;
            case 3:
                logger.info("\nAdd new dvd\nEnter:\n-Title- ");
                scanner.nextLine();
                title = scanner.nextLine();
                logger.info("-Release date (dd/mm/yyyy)- ");
                date = scanner.nextLine();
                logger.info("-MPAA rating- ");
                MPAARating = scanner.nextDouble();
                logger.info("-Director- ");
                scanner.nextLine();
                director = scanner.nextLine();
                logger.info("-Studio- ");
                studio = scanner.nextLine();
                logger.info("-User rating- ");
                userRating = scanner.nextDouble();
                dvdLibraryDAO.addDVD(title,date,MPAARating,director,studio,userRating);
                break;
            case 4:
                logger.info("\n-Enter the DVD' id- ");
                dvdLibraryDAO.deleteDVD(scanner.nextInt());
                break;
            case 5:
                logger.info("\nEdit a dvd\n-Enter DVD' id- ");
                id = scanner.nextInt();
                DVDLibrary dvdLibrary = dvdLibraryDAO.findById(id);
                logger.info("Enter new record or type 'keep' or 9999 for numeric fields to keep existing one:\n-Title- ");
                scanner.nextLine();
                title = scanner.nextLine();
                if(title.equals("keep")) title = dvdLibrary.getTitle();
                logger.info("-Release date (dd/mm/yyyy)- ");
                date = scanner.nextLine();
                if(date.equals("keep")) date = dvdLibrary.getReleaseDate().toString();
                logger.info("-MPAA rating- ");
                MPAARating = scanner.nextDouble();
                if(MPAARating == (double) 9999) MPAARating = dvdLibrary.getMPAARating();
                logger.info("-Director- ");
                scanner.nextLine();
                director = scanner.nextLine();
                if(director.equals("keep")) director = dvdLibrary.getDirectorName();
                logger.info("-Studio- ");
                studio = scanner.nextLine();
                if(studio.equals("keep")) studio = dvdLibrary.getStudio();
                logger.info("-User rating- ");
                userRating = scanner.nextDouble();
                if(userRating == (double) 9999) userRating = dvdLibrary.getUserRating();
                dvdLibraryDAO.editDVD(id,title,date,MPAARating,director,studio,userRating);
                break;
            case 6:
                logger.info("\n-Enter the DVD' title- ");
                scanner.nextLine();
                title = scanner.nextLine();
                dvdLibraryDAO.showDVD(title);
                break;
            case 7:
                dvdLibraryDAO.showAll();
                break;
            case 8:
                dvdLibraryDAO.clear();
                logger.info("The library is empty now");
                break;
            case 9:
                choice = 0;
                break;
            default:
                logger.warning("Wrong entry\nTry again");
                session(dvdLibraryDAO);
                break;
        }
        if(choice!=0) session(dvdLibraryDAO);
    }

    /**
     * This function is used to display the menu options to the user
     */
    private static void menu(){
        logger.info("\n-DVD Library-\n" + "-MENU-\n" +
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

}
