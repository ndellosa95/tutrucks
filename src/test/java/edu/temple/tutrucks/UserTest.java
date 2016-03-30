package edu.temple.tutrucks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michn_000
 */

import org.junit.*;
import static org.junit.Assert.*;


public class UserTest {
    private ItemReview itemReview;
    private TruckReview truckReview;  
    private User user;
    
   @Before
    public void setUpMock() {
        itemReview = new ItemReview();   
        truckReview =  new TruckReview();
        user = new User();               
    }
    
    @Test
    public void testMockCreation(){
        assertNotNull(user);
        assertNotNull(truckReview);
        assertNotNull(itemReview);
    }

   @Test
    public void testAddItemReview() {
        itemReview.setUser(user);
        user.addReview(itemReview);
        System.out.println("Verifying that the item review was added to the user list");
        assertEquals(user.getItemReviews().get(0), itemReview);
    }
    
    @Test
    public void testAddTruckReview() {
        truckReview.setUser(user);
        user.addReview(truckReview);
        System.out.println("Verifying that the truck review was added to the user list");
        assertEquals(user.getTruckReviews().get(0), truckReview);
    }
   
    @Test
    public void testAddReviewFailed() {
        User temp = new User();
        itemReview.setUser(temp);
        user.addReview(itemReview);
        System.out.println("Verifying that the item review was not added to the user list");
        assertEquals(user.getTruckReviews().contains(itemReview), false);
    }
    /*
    @Test
    public void encryptionTest() {
        String password = "password";
        Random salter = new java.security.SecureRandom();
        byte[] salt = new byte[16];
        salter.nextBytes(salt);
        String encrypted = User.encryptPassword(password, salt);
        System.out.println("encrypted password: " + encrypted);
    } */
    /*
    @Test
    public void createUser() {
        String email = "nick.dellosa@yahoo.com";
        String password = "myninnie";
        User me = User.createUser(email, password, false, null, null);
        assertEquals(me.getUserEmail(), email);
        String eps = me.getPassWord();
        assertNotEquals(eps, password);
        System.out.println(eps);
    } */
}






