/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

/**
 * This enum represents the different levels of permissions users can have. 
 * 
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 */
public enum Permissions {
    
    PLEB(0), ADMIN(1);
    
    private int permissions;
    
    private Permissions(int p) {
        this.permissions = p;
    }
}
