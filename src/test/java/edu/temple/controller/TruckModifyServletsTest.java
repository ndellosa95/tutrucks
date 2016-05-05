/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Permissions;
import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.User;
import java.io.IOException;
import java.util.HashSet;
import javax.servlet.ServletException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public abstract class TruckModifyServletsTest extends ServletTest {

    protected static Tag bsTag1;
    protected static Tag bsTag2;
    protected Truck truck;
    protected static User user;
    protected static HashSet<Truck> truckSet;

    @BeforeClass
    public static void setupClass() {
        truckSet = new HashSet<>();
        bsTag1 = Tag.retrieveTag("bsTag1", true);
        bsTag2 = Tag.retrieveTag("bsTag2", true);
        user = User.createUser("tmst@test.com", "password", false, null, null, null);
        user.setPermissions(Permissions.ADMIN);
        user.save();
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            bsTag1.delete();
            bsTag2.delete();
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getMessage());
        }
        user.delete();
        for (Truck t : truckSet) t.delete();
    }
    
    @Before
    @Override
    public void setupInstance() throws IOException {
        super.setupInstance();
        when(session.getAttribute("user")).thenReturn(user);
    }
}
