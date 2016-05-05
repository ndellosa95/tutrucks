/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Truck;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class AddTruckServletTest extends TruckModifyServletsTest {
    
    private static final String TRUCKNAME = "atsTruck";
    
    @Test
    public void testProcessRequest() throws IOException {
        doNothing().when(response).setContentType(anyString());
        when(request.getParameter("name")).thenReturn(TRUCKNAME);
        when(request.getParameter("lat")).thenReturn("4.13");
        when(request.getParameter("lng")).thenReturn("4.13");
        when(request.getParameter("open")).thenReturn("00:00:00");
        when(request.getParameter("close")).thenReturn("23:59:59");
        when(request.getParameter("tags")).thenReturn("bsTag1, bsTag2");
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        try {
            new AddTruckServlet().processRequest(request, response);
            truck = Truck.getTruckByName(TRUCKNAME);
            assertTrue(truck != null);
            if (truck != null) truckSet.add(truck);
        } catch (ServletException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }
}
