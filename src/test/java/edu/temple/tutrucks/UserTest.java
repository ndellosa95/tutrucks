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

import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;


public class UserTest {
    private ItemReview itemReview;
    private TruckReview truckReview;  
    private User user;
    private static User realUser;
    private static final String EMAIL = "usertest@test.com";
    
    @BeforeClass
    public static void setup() {
        realUser = User.createUser(EMAIL, "password", false, null, null, null);
    }
    
    @AfterClass
    public static void tearDown() {
        realUser.delete();
    }
    
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
    
    @Test
    public void testCreateUser() {
        assertEquals(EMAIL, realUser.getUserEmail());
    }
    
    @Test
    public void testValidateUserFacebook() {
        String email = "testfacebook@test.com";
        String fbID = "5555";
        User facebookUser1 = User.createUser(email, "password", true, "Facebook User", null, fbID);
        User facebookUser2 = User.validateUserFacebook(email, fbID);
        assertEquals(facebookUser1, facebookUser2);
        facebookUser1.delete();
    }
    
    @Test
    public void testValidateUser() {
        User testUser = User.validateUser(EMAIL, "password");
        assertEquals(realUser, testUser);
    }
    
    @Test
    public void testValidateUserMultiple() {
        for (int i=0; i < 5; i++) {
            User testUser = User.validateUser(EMAIL, "password");
            assertEquals(realUser, testUser);
        }
    }
    
    @Test
    public void testChangePassword() {
        User testUser = User.createUser("testaccount2@test.com", "password", false, null, null, null);
        byte[] testUserPassword = testUser.getPassWord();
        byte[] testUserSalt = testUser.getSalt();
        testUser.changePassword("password2");
        assertFalse(Arrays.equals(testUserSalt, testUser.getSalt()));
        assertFalse(Arrays.equals(testUserPassword, testUser.getPassWord()));
        testUser.delete();
    }
}






