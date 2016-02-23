/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

/**
 *
 * @author nickdellosa
 */
public interface Taggable {
    public java.util.Set getTags();
    public void addTags(Tag... t);
}
