package com.red.dao;

import com.red.entity.DVDLibrary;


// This is the interface for the DAO.
public interface DVDLibraryDAOI {

    public void addDVD(String title, String date, Double MPAARating, String director, String studio, Double userRating);

    public void deleteDVD(int id);

    public void editDVD(int id,String title, String date, Double MPAARating, String director, String studio, Double userRating);

    public void showAll();

    public void showDVD(String title);

    public DVDLibrary findByTitle(String title);

    public DVDLibrary findById(int id);

    public void toFile();

    public void fromFile();

}
