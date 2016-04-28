/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.User;
import edu.temple.tutrucks.Visualizable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author nickdellosa
 */
public class UploadImageServlet extends HttpServlet {
    
    private static final String IMAGE_UPLOADS = "/uploads/";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part imagePart = req.getPart("image");
            String entityType = req.getParameter("type");
            int id = -1;
            Visualizable v = null;
            switch (entityType) {
                case "truck":
                    id = Integer.parseInt(req.getParameter("id"));
                    v = Truck.getTruckByID(id);
                    if (v == null) {
                        // error handling
                        return;
                    }
                    break;
                case "user":
                    User user = (User)req.getSession().getAttribute("user");
                    if (user == null) {
                        // error handling
                        return;
                    }
                    id = user.getId();
                    v = user;
                    break;
                default:
                    //error handling
                    return;
            }
            BufferedImage image = ImageIO.read(imagePart.getInputStream());
            File output = new File(IMAGE_UPLOADS + entityType + "/" + id + ".png");
            ImageIO.write(image, "png", output);
            v.setAvatar(output.getPath());
        } catch (IOException ex) {
        } catch (ServletException ex) {
        } catch (NumberFormatException ex) {
        } catch (ClassCastException ex) {
        }
    }
    
}
