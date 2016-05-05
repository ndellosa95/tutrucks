/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Permissions;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michn_000
 */
public class DeleteTruckServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getPermissions() != Permissions.ADMIN) {
            response.sendError(403);
        }
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("truckId"));
        Truck deleteTruck = Truck.getTruckByID(id);
        if (deleteTruck != null) {
            deleteTruck.delete();
            try (PrintWriter out = response.getWriter()) {
                out.println("Truck deleted");
            }
        }
    }
}
