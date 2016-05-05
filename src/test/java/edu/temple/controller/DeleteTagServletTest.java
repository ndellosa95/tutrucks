/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
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
public class DeleteTagServletTest extends ServletTest {
    
    private static final String TEST_TAG_NAME = "dtstag";
    
    @Test
    public void testProcessRequest() throws IOException, ServletException {
        Tag.retrieveTag(TEST_TAG_NAME, true);
        doNothing().when(response).setContentType(anyString());
        when(request.getParameter("name")).thenReturn(TEST_TAG_NAME);
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        new DeleteTagServlet().processRequest(request, response);
        Tag retval = Tag.retrieveTag(TEST_TAG_NAME, false);
        assertTrue(retval == null);
    }
}
