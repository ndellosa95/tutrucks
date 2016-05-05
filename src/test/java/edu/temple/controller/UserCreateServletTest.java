/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.User;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author nickdellosa
 */
public class UserCreateServletTest extends ServletTest {
    
    private static final String EMAIL = "ucs@test.com";
    private static final String PASSWORD = "password";
    private static boolean sessionWorked = false;
    
    private static final Answer ucsAnswer = new Answer() {
        @Override
        public Object answer(InvocationOnMock invocation) {
            Object[] os = invocation.getArguments();
            sessionWorked = (os[os.length-1] != null);
            return os[os.length-1];
        }
    };
  
    @Test
    public void testDoPost() throws IOException {
        when(request.getSession(true)).thenReturn(session);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getParameter("facebook")).thenReturn("true");
        when(request.getParameter("display")).thenReturn("Hodor!");
        when(request.getParameter("facebook_id")).thenReturn("whatever");
        doAnswer(ucsAnswer).when(session).setAttribute(eq("user"), any());
        new UserCreateServlet().doPost(request, response);
        User user = User.validateUser(EMAIL, PASSWORD);
        assertFalse(user == null);
        assertTrue(sessionWorked);
        if (user != null) {
            user.delete();
        }
    }
}
