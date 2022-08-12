package com.red.dao;

import com.red.entity.DVDLibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DVDLibraryDAO implements DVDLibraryDAOI {

    private  static Logger logger;
    private static List<DVDLibrary> dvds;

    static {
        dvds = new ArrayList<>();
        logger = Logger.getLogger(DVDLibraryDAO.class.getName());
    }


    @Override
    public void addDVD(String title, Date date, Double MPAARating, String director, String studio, Double userRating) {
        dvds.add(new DVDLibrary(id(),title,date,MPAARating,director,studio,userRating));
        logger.info("The DVD was added");
    }

    @Override
    public void deleteDVD(int id) {
        DVDLibrary dvdLibrary = findById(id);
       if(!dvdLibrary.equals(null)){
           dvds.remove(dvdLibrary);
           logger.info("The dvd was successfully deleted");
       } else logger.warning("There's no such dvd");
    }

    @Override
    public void editDVD(int id,String title, Date date, Double MPAARating, String director, String studio, Double userRating) {
        DVDLibrary dvdLibrary = findById(id);
        if(!dvdLibrary.equals(null)){
            dvds.set(id,new DVDLibrary(id,title,date,MPAARating,director,studio,userRating));
            logger.info("The dvd was successfully updated");
        } else logger.warning("There's no such dvd");

    }

    @Override
    public void showAll() {
        for(DVDLibrary dvdLibrary:dvds) logger.info(dvdLibrary.toString());
    }

    @Override
    public void showDVD(String title) {
       logger.info(findByTitle(title).toString());
    }

    protected int id(){
        if(dvds.isEmpty()) return 0;
        else return dvds.get(dvds.size()).getId() + 1;
    }

    public DVDLibrary findById(int id){
        DVDLibrary dvdLibrary = dvds.stream().filter(dvd -> (id == dvd.getId())).findAny().orElse(null);
        return dvdLibrary;
    }

    @Override
    public DVDLibrary findByTitle(String title){
        DVDLibrary dvdLibrary = dvds.stream().filter(dvd -> (title.equals(dvd.getTitle()))).findAny().orElse(null);
        return dvdLibrary;
    }
}
