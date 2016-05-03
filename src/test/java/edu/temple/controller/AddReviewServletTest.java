/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemReview;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.TruckReview;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */ /*
public class AddReviewServletTest extends IntegrationTestUsingResources {
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private static final String REVIEWTEXT = "this is a test review";
    
    @Before
    public void setup() throws IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(IntegrationTestResources.getTestUser());
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("text")).thenReturn(REVIEWTEXT);
        when(request.getParameter("rating")).thenReturn("5");
        doNothing().when(response).sendRedirect(anyString());
    }
    
    @Test
    public void testDoPostTruck() {
        when(request.getParameter("type")).thenReturn("truck");
        when(request.getParameter("id")).thenReturn("1");
        new AddReviewServlet().doPost(request, response);
        Truck truck = Truck.getTruckByID(1);
        List<TruckReview> reviews = truck.loadReviews();
        boolean seen = false;
        for (TruckReview tr : reviews) {
            seen = (tr.getUser().equals(IntegrationTestResources.getTestUser()) && tr.getReviewStars() == 5 && tr.getReviewText().equals(REVIEWTEXT));
        }
        assertTrue(seen);
    }
    
    @Test
    public void testDoPostItem() {
        when(request.getParameter("type")).thenReturn("item");
        when(request.getParameter("id")).thenReturn("1");
        new AddReviewServlet().doPost(request, response);
        Item item = Item.getItemByID(1);
        List<ItemReview> reviews = item.loadReviews();
        boolean seen = false;
        for (ItemReview ir : reviews) {
            seen = (ir.getUser().equals(IntegrationTestResources.getTestUser()) && ir.getReviewStars() == 5 && ir.getReviewText().equals(REVIEWTEXT));
        }
        assertTrue(seen);
    }
} */
