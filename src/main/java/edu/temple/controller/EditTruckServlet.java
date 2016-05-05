/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.HibernateUtil;
import edu.temple.tutrucks.Permissions;
import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author michn_000
 */

public class EditTruckServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getPermissions() != Permissions.ADMIN) {
            response.sendError(403);
        }
        response.setContentType("text/html;charset=UTF-8");
        int truckId = Integer.parseInt(request.getParameter("id"));
        String truckName = request.getParameter("name");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lng = Double.parseDouble(request.getParameter("lng"));
        Time openTime = Time.valueOf(request.getParameter("open"));
        Time closeTime = Time.valueOf(request.getParameter("close"));
        String tagString = request.getParameter("tags");
        List<String> tags = Arrays.asList(tagString.split("\\s*,\\s*"));
        Truck editTruck = Truck.getTruckByID(truckId, false, true);
        editTruck.setTruckName(truckName);
        editTruck.setLongitude(lng);
        editTruck.setLatitude(lat);
        editTruck.setOpeningTime(openTime);
        editTruck.setClosingTime(closeTime);
        Set<Tag> deletedTags = editTruck.getTags();
        for (String s : tags) {
            Tag temp = Tag.retrieveTag(s, true);
            deletedTags.remove(temp);
            temp.addEntity(editTruck);
            editTruck.addTags(temp);
            temp.save();
        }
        for (Tag t: deletedTags) {
            Set<Truck> currentTaggedTrucks = t.getTrucks();
            currentTaggedTrucks.remove(editTruck);
            t.setTrucks(currentTaggedTrucks);
            Set<Tag> currentTags = editTruck.getTags();
            currentTags.remove(t);
            editTruck.setTags(currentTags);
            t.save();
        }
        editTruck.save();
        try (PrintWriter out = response.getWriter()) {
           out.print("Truck successfully updated");
        }
    }
}
