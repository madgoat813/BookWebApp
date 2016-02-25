/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.util.Date;

/**
 *
 * @author Taylor
 */
public class Author {
    private int authorID;
    private String authorName;
    private Date dateAdded;

    public Author() {
        this.dateAdded = new Date();
    }

    public Author(int authorID) {
        this.authorID = authorID;
        this.dateAdded = new Date();
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.authorID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorID != other.authorID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorID=" + authorID + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    
    
}

