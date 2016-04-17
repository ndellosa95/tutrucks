/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.Date;

/**
 * The superclass for all review objects. Contains common traits of reviews.
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 * @param <T> The type of item being reviewed. Must implement the reviewable interface
 */
public abstract class Review<T extends Reviewable> {
    
     int id;
     User user;
     int reviewStars;
     String reviewText;
     Date reviewDate;

     /**
      * Returns the reviewed entity. Required by Hibernate
      * @return the object that is the subject of the review
      */
    public abstract T getReviewed();
    /**
     * Sets the object being reviewed. Required by Hibernate
     * @param reviewed the object being reviewed
     */
    public abstract void setReviewed(T reviewed);
    /**
     * Returns the user who wrote the review. Required by Hibernate
     * @return the user who wrote the review
     */
    public User getUser() {
        return user;
    }
    /**
     * Sets the user who wrote the review. Required by Hibernate
     * @param user the user who wrote the review
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Returns the number of stars in the review. Required by Hibernate
     * @return the number of stars given in the review
     */
    public int getReviewStars() {
        return reviewStars;
    }
    /**
     * Sets the number of stars in the review. Must be between 0 and 5. Required by Hibernate
     * @param reviewStars the number of stars given in the review. Must be between 0 and 5.
     */
    public void setReviewStars(int reviewStars) {
        if (reviewStars > 10 || reviewStars < 0) {
            //error handling
            return;
        }
        this.reviewStars = reviewStars;
    }
    /**
     * Returns the text of the review. Required by Hibernate
     * @return the text of the review
     */
    public String getReviewText() {
        return reviewText;
    }
    /**
     * Sets the text of the review. Required by Hibernate
     * @param reviewText the text of the review
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    /**
     * Returns the date on which the review was written. Required by Hibernate
     * @return the date of the review
     */
    public Date getReviewDate() {
        return reviewDate;
    }
    /**
     * Sets the date of the review. Required by Hibernate
     * @param reviewDate the date of the review
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
    /**
     * Returns a review's ID. Required by Hibernate
     * @return this review's ID
     */
    public Integer getId() {
        return this.id;
    }
    /**
     * Sets a review's ID. Required by Hibernate
     * @param id the ID of this review.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
