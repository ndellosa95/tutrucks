/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.User;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class AddTagServletTest extends ServletTest {
    
    private static User user;
    
    @BeforeClass
    public static void setup() {
        user = User.createUser("ats@test.com", "password", false, null, null, null);
    }
    
    @AfterClass
    public static void tearDown() {
        user.delete();
    }
    
    @Test
    public void testDoPostTruck() throws IOException {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("names")).thenReturn("atst1,atst2");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("truck");
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        new AddTagServlet().doPost(request, response);
        Tag tag1 = Tag.retrieveTag("atst1", false);
        assertTrue(tag1 != null);
        Tag tag2 = Tag.retrieveTag("atst2", false);
        assertTrue(tag2 != null);
        if (tag1 != null) tag1.delete();
        if (tag2 != null) tag2.delete();
    }
    
    @Test
    public void testDoPostItem() throws IOException {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("names")).thenReturn("atsi1,atsi2");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("item");
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        new AddTagServlet().doPost(request, response);
        Tag tag1 = Tag.retrieveTag("atsi1", false);
        assertTrue(tag1 != null);
        Tag tag2 = Tag.retrieveTag("atsi2", false);
        assertTrue(tag2 != null);
        if (tag1 != null) tag1.delete();
        if (tag2 != null) tag2.delete();
    }
}
