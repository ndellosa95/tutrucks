/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Truck;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
public class DeleteTruckServletTest extends TruckModifyServletsTest {
    
    @Test
    public void testProcessRequest() throws ServletException, IOException {
        truck = new Truck();
        truck.setTruckName("dtsTruck");
        truck.setLatitude(4.13);
        truck.setLongitude(4.13);
        truck.setOpeningTime(new Time(0));
        truck.setClosingTime(new Time(61395));
        truck.save();
        when(request.getParameter("truckId")).thenReturn(String.valueOf(truck.getId()));
        PrintWriter fakeWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(fakeWriter);
        doNothing().when(fakeWriter).print(anyString());
        new DeleteTruckServlet().processRequest(request, response);
        Truck truckReloaded = Truck.getTruckByID(truck.getId());
        assertTrue(truckReloaded == null);
        if (truckReloaded != null) truckReloaded.delete();
    }
}
