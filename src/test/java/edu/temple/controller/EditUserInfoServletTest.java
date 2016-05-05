/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.User;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class EditUserInfoServletTest extends ServletTest {
    
    private static final String EMAIL1 = "euis1@test.com";
    private static final String EMAIL2 = "euis2@test.com";
    private static final String PASSWORD = "password";
    private static final String HODOR = "Hodor!";
    
    @Test
    public void testDoPostDisplayName() throws IOException {
        User user = User.createUser(EMAIL1, PASSWORD, false, null, null, null);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("displayname")).thenReturn(HODOR);
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        doNothing().when(response).sendRedirect(anyString());
        new EditUserInfoServlet().doPost(request, response);
        User user2 = User.validateUser(EMAIL1, PASSWORD);
        assertTrue(user2.getDisplayName().equals(HODOR));
        if (user2 != null) user2.delete();
    }
    
    @Test
    public void testDoPostPassword() throws IOException {
        User user = User.createUser(EMAIL2, PASSWORD, false, null, null, null);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("oldpassword")).thenReturn(PASSWORD);
        when(request.getParameter("newpassword")).thenReturn(HODOR);
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        doNothing().when(response).sendRedirect(anyString());
        new EditUserInfoServlet().doPost(request, response);
        User user2 = User.validateUser(EMAIL2, HODOR);
        assertFalse(user2 == null);
        if (user2 != null) user2.delete();
    }
}
