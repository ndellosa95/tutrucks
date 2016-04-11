/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.User;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickdellosa
 */
public class UserCreateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean fb = Boolean.parseBoolean(req.getParameter("facebook"));
        String display, avatar;
        display = avatar = null;
        if (fb) {
            display = req.getParameter("display");
            avatar = req.getParameter("avatar");
        }
        User user = User.createUser(email, password, fb, display, avatar);
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("/");
    }
}
