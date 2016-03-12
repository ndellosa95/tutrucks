/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

/**
 *
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 * This interface should be implemented by all classes which can have reviews written about them. 
 */
public interface Reviewable {
    /**
     * Associates a review with an object.
     * @param r The review to be associated
     */
    public void addReview(Review r);
}
