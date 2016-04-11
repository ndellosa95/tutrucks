/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.mockito.Mockito.*;

/**
 *
 * @author michn_000
 */
public class TruckTest {
    private TruckReview review;
    private Tag tag;
    private Truck truck;
    private Set<Truck> truckSet;
    private List<Tag> tagList;
    
    @Before
    public void setUpMock() {
        review = mock(TruckReview.class);   
        tag = mock(Tag.class);
        truck = new Truck();
        truckSet = new HashSet();       
        tagList = new ArrayList();
        for (int i =0; i < 5; i++) {
            Tag tempTag = mock(Tag.class);
            tagList.add(tempTag);
        }
    }
    
    @Test
    public void testMockCreation(){
        assertNotNull(review);
        assertNotNull(tag);
        assertNotNull(truck);
        assertNotNull(truckSet);
        assertNotNull(tagList);
    }

    @Test
    public void testAddReview() {
        when(review.getReviewed()).thenReturn(truck);  
        truck.addReview(review);
        System.out.println("Verifying review called getReviewed");
        verify(review).getReviewed();
        System.out.println("Verifying if condition was false");
        assertEquals(true, review.getReviewed().equals(truck));
        System.out.println("Verifying that the review was added to the list");
        assertEquals(truck.getTruckReviews().get(0), review);
    }
    /*
    @Test
    public void testAddReviewFailed() {
        when((Reviewable)review.getReviewed()).thenReturn(new Item());  
        truck.addReview(review);
        System.out.println("Verifying review called getReviewed");
        verify(review).getReviewed();
        System.out.println("Verifying if condition was true");
        assertEquals(false, review.getReviewed().equals(truck));
        System.out.println("Verifying that the review was not added to the list");
        assertNotEquals(truck.getTruckReviews().contains(review), review);        
    } */

   @Test
    public void testAddTagTruckInTagSet() { 
        truckSet.add(truck);
        when(tag.getTrucks()).thenReturn(truckSet);
        truck.addTags(tag);
        System.out.println("Verifying tag called get trucks");
        verify(tag).getTrucks();
        System.out.println("Verifying tag was added to truck");
        assertEquals(truck.getTags().contains(tag), true);
    }
    
    @Test
    public void testAddTagTruckNotInTagSet() { 
        when(tag.getTrucks()).thenReturn(truckSet);
        truck.addTags(tag);
        System.out.println("Verifying tag called get trucks");
        verify(tag).getTrucks();
        System.out.println("Verifying truck added to tag");
        verify(tag).addEntity(truck);
        System.out.println("Verifying tag added the truck");
        assertEquals(truck.getTags().contains(tag), true);
    }
    
    @Test
    public void testAddMultipleTags() { 
        truckSet.add(truck);
        for (int i = 0; i < tagList.size(); i++) {
            when(tagList.get(i).getTrucks()).thenReturn(truckSet);
        }
        truck.addTags(tagList.get(0), tagList.get(1), tagList.get(2), tagList.get(3), tagList.get(4));
        System.out.println("Verifying all tags called get trucks");
         for (int i = 0; i < tagList.size(); i++) {
             verify(tagList.get(i)).getTrucks();
        }
        System.out.println("Verifying all tags were added to truck");    
        for (int i = 0; i < tagList.size(); i++) {
             assertEquals(truck.getTags().contains(tagList.get(i)), true);
        } 
    }
    
    
    //INTEGRATION TESTING
    
    @Test
    public synchronized void testSearchTrucks() {
        String searchTerms = "c";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Truck where truckName like '%" + searchTerms + "%'"
        );
        List l = q.list();
        session.close();
        List<Searchable> testResults = Searchable.SearchOrganizer.organize(l, searchTerms);
        List<Truck> results = Truck.searchTrucks(searchTerms);
        for (int i = 0; i < testResults.size(); i++) {
            assertEquals(testResults.get(i).getSearchName(), results.get(i).getSearchName());
        }
    }

    @Test
    public void testAddReviewIntegration() {
        Review newTruckReview = new TruckReview();
        newTruckReview.setReviewText("review text");
        newTruckReview.setReviewDate(new Date());
        newTruckReview.setReviewStars(2);
        newTruckReview.setReviewed(truck);
        
        
    }
    
    
    @Test
    public void testAddTagIntegration() {
        
    }
    
    @Test
    public void testAddMultipleTagsIntegration() {
        
    }
    
    @Test
    public void testAddReviewFailIntegration() {
        
    }
    
    @Test
    public void testLoadReviews() {
        Truck t;
        List<TruckReview> reviews;
        t = Truck.getTruckByID(9);
        reviews = t.loadReviews();
        assertTrue(reviews.size() > 0);
        for (TruckReview r : reviews) {
            if (r != null) System.out.println(r.reviewText);
            else System.out.println("null entry");
        }
    }
    
    
}

