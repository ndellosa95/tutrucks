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
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author nickdellosa
 */
public class UserLoginLogoutServletTest extends ServletTest {
    
    private static final String EMAIL1 = "ulls1@test.com";
    private static final String EMAIL2 = "ulls2@test.com";
    private static final String PASSWORD = "password";
    private boolean loggedIn;
    
    private final Answer ullsAnswer = new Answer() {
        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            Object[] os = invocation.getArguments();
            loggedIn = (os[os.length-1] != null);
            return os[os.length-1];
        }
    };
    
    @Before
    @Override
    public void setupInstance() throws IOException {
        super.setupInstance();
        when(request.getSession(true)).thenReturn(session);
        doAnswer(ullsAnswer).when(session).setAttribute(eq("user"), any());
    }
    
    @Test
    public void testDoPostLogin() throws IOException {
        loggedIn = false;
        User user = User.createUser(EMAIL1, PASSWORD, false, null, null, null);
        when(request.getServletPath()).thenReturn("login");
        when(request.getParameter("email")).thenReturn(EMAIL1);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        doNothing().when(response).sendRedirect(anyString());
        new UserLoginLogoutServlet().doPost(request, response);
        assertTrue(loggedIn);
        user.delete();
    }
    
    @Test
    public void testDoPostLogout() throws IOException {
        loggedIn = true;
        User user = User.createUser(EMAIL2, PASSWORD, false, null, null, null);
        when(request.getServletPath()).thenReturn("logout");
        when(session.getAttribute("user")).thenReturn(user);
        new UserLoginLogoutServlet().doPost(request, response);
        assertFalse(loggedIn);
        user.delete();
    }
}
