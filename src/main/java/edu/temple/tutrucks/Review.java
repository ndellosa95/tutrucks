/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.Date;

/**
 *
 * @author nickdellosa
 */
public class Review<T extends Reviewable> {
    
     private T reviewed;
     private User user;
     private int reviewStars;
     private String reviewText;
     private Date reviewDate;


    public Reviewable getReviewed() {
        return reviewed;
    }

    public void setReviewed(T reviewed) {
        this.reviewed = reviewed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReviewStars() {
        return reviewStars;
    }

    public void setReviewStars(int reviewStars) {
        if (reviewStars > 5) {
            //error handling
            return;
        }
        this.reviewStars = reviewStars;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
