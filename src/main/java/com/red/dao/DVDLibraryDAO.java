package com.red.dao;

import com.red.AppController;
import com.red.entity.DVDLibrary;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * DVD Library data access object class
 */
public class DVDLibraryDAO implements DVDLibraryDAOI {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static Logger logger;

    // It's a static variable that stores the list of DVDLibrary objects.
    private static List<DVDLibrary> dvds;

    static {
        dvds = new ArrayList<>();
        logger = Logger.getLogger(DVDLibraryDAO.class.getName());
    }



    /**
     * The function takes in a title, date, MPAARating, director, studio, and userRating and adds it to the list of DVD Library
     *
     * @param title      The title of the DVD
     * @param date       The date the DVD was released.
     * @param MPAARating The MPAA rating of the DVD. This is a double value between 0 and 10.
     * @param director   The director of the movie
     * @param studio     The studio that produced the movie
     * @param userRating The user's rating of the DVD
     */
    @Override
    public void addDVD(String title, String date, Double MPAARating, String director, String studio, Double userRating) {
        LocalDate dt = null;
        if (null != date && date.trim().length() > 0) dt = LocalDate.parse(date, format);
        MPAARating = MPAARating > 10 ? 10 : MPAARating < 0 ? 0 : MPAARating;
        userRating = userRating > 10 ? 10 : userRating < 0 ? 0 : userRating;
        dvds.add(new DVDLibrary(id(), title, dt, MPAARating, director, studio, userRating));
        logger.info("The DVD was added");
    }

    /**
     * The function takes an id as a parameter and searches for a DVD with that id. If it finds one, it removes it from the
     * list. If it doesn't find one, it logs a warning
     *
     * @param id the id of the DVD to be deleted
     */
    @Override
    public void deleteDVD(int id) {
        DVDLibrary dvdLibrary = findById(id);
        if (!dvdLibrary.equals(null)) {
            dvds.remove(dvdLibrary);
            logger.info("The DVD was successfully deleted");
        } else logger.warning("There's no such DVD");
    }

    /**
     * If the DVD exists, update it with the new values
     *
     * @param id         the id of the DVD to be edited
     * @param title      The title of the DVD
     * @param date       The date the DVD was released
     * @param MPAARating The MPAA rating of the movie.
     * @param director   The director of the movie
     * @param studio     The studio that produced the movie
     * @param userRating The user's rating of the movie
     */
    @Override
    public void editDVD(int id, String title, String date, Double MPAARating, String director, String studio, Double userRating) {
        DVDLibrary dvdLibrary = findById(id);
        if (!dvdLibrary.equals(null)) {
            LocalDate dt = null;
            if (null != date && date.trim().length() > 0) dt = LocalDate.parse(date, format);
            MPAARating = MPAARating > 10 ? 10 : MPAARating < 0 ? 0 : MPAARating;
            userRating = userRating > 10 ? 10 : userRating < 0 ? 0 : userRating;
            dvds.set(id, new DVDLibrary(id, title, dt, MPAARating, director, studio, userRating));
            logger.info("The DVD was successfully updated");
        } else logger.warning("There's no such DVD");
    }

    /**
     * If the library is empty, print a warning message. Otherwise, print all the DVDs in the library
     */
    @Override
    public void showAll() {
        if (dvds.isEmpty()) logger.warning("The library is empty");
        else for (DVDLibrary dvdLibrary : dvds) logger.info(dvdLibrary.toString());
    }

    /**
     * If the DVD exists, print it out. If it doesn't, print out a warning message
     *
     * @param title The title of the DVD to be shown.
     */
    @Override
    public void showDVD(String title) {
        DVDLibrary dvdLibrary = findByTitle(title);
        if (dvdLibrary != null) logger.info(dvdLibrary.toString());
        else logger.warning("There's no such DVD");
    }

    /**
     * > This function returns the next available id for a new DVD
     *
     * @return The id of the next DVD.
     */
    protected int id() {
        if (dvds.isEmpty()) return 0;
        else return dvds.get(dvds.size() - 1).getId() + 1;
    }

    /**
     * The function takes in an integer, and returns a DVDLibrary object
     *
     * @param id the id of the DVD you want to find
     * @return The DVDLibrary object with the matching id.
     */
    @Override
    public DVDLibrary findById(int id) {
        DVDLibrary dvdLibrary = dvds.stream().filter(dvd -> (id == dvd.getId())).findAny().orElse(null);
        return dvdLibrary;
    }

    /**
     * It saves the library to a file.
     */
    @Override
    public void toFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/DVDLibrary.lbb"))) {
            out.writeObject(dvds);
            logger.info("The library was successfully saved");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * It reads the file and loads the data into the program.
     */
    @Override
    public void fromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/DVDLibrary.lbb"))) {
            dvds.clear();
            dvds = (ArrayList) in.readObject();
            logger.info("The library was successfully loaded");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The function takes in a title as a string, and returns a DVDLibrary object that matches the title, or null if no
     * match is found.
     *
     * @param title The title of the DVD you want to find.
     * @return The DVDLibrary object is being returned.
     */
    @Override
    public DVDLibrary findByTitle(String title) {
        DVDLibrary dvdLibrary = dvds.stream().filter(dvd -> (title.equals(dvd.getTitle()))).findAny().orElse(null);
        return dvdLibrary;
    }

    /**
     * This function clears the list of dvds
     */
    @Override
    public void clear() {
        dvds.clear();
    }
}
