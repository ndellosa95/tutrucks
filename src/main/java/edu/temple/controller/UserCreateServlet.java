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
    
    private static final String EMAIL_VERIFICATION = "(.+)@(.+)\\.((com)|(edu)|(org)|(gov))";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String page = req.getParameter("currentpage");
        if (!email.matches(EMAIL_VERIFICATION)) {
            // redirect back to registration
        } else if (password.length()>16 || password.length()<6) {
            // redirect back to registration
        }
        boolean fb = Boolean.parseBoolean(req.getParameter("facebook"));
        String display, avatar, fbID;
        display = avatar = fbID = null;
        if (fb) {
            display = req.getParameter("display");
            avatar = req.getParameter("avatar");
            fbID = req.getParameter("facebook_id");
        }
        User user = User.createUser(email, password, fb, display, avatar, fbID);
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect(page==null?"/":page);
    }
}
