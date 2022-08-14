package com.red.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * This class is a POJO that represents a DVD
 */
public class DVDLibrary implements Serializable {

    private static transient final DecimalFormat df = new DecimalFormat("0.00");
    private int id;
    private String title;
    private LocalDate releaseDate;
    private Double MPAARating;
    private String directorName;
    private String studio;
    private Double userRating;

    public DVDLibrary(int id, String title, LocalDate releaseDate, Double MPAARating, String directorName, String studio, Double userRating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.MPAARating = MPAARating;
        this.directorName = directorName;
        this.studio = studio;
        this.userRating = userRating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(Double MPAARating) {
        this.MPAARating = MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DVDLibrary that = (DVDLibrary) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (MPAARating != null ? !MPAARating.equals(that.MPAARating) : that.MPAARating != null) return false;
        if (directorName != null ? !directorName.equals(that.directorName) : that.directorName != null) return false;
        if (studio != null ? !studio.equals(that.studio) : that.studio != null) return false;
        return userRating != null ? userRating.equals(that.userRating) : that.userRating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (MPAARating != null ? MPAARating.hashCode() : 0);
        result = 31 * result + (directorName != null ? directorName.hashCode() : 0);
        result = 31 * result + (studio != null ? studio.hashCode() : 0);
        result = 31 * result + (userRating != null ? userRating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nDVD {\n" +
                "id - " + id +
                ",\ntitle - " + title +
                ",\nreleaseDate - " + releaseDate +
                ",\nMPAARating - " + df.format(MPAARating) +
                ",\nDirectorName - " + directorName +
                ",\nStudio - " + studio +
                ",\nUserRating - " + df.format(userRating) +
                "\n}";
    }
}
