/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import javax.servlet.annotation.MultipartConfig;
import edu.temple.tutrucks.HibernateUtil;
import edu.temple.tutrucks.Permissions;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.User;
import edu.temple.tutrucks.Visualizable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.hibernate.Session;

/**
 *
 * @author nickdellosa
 */
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)

public class UploadImageServlet extends HttpServlet {
    
    private static final String IMAGE_UPLOADS = "uploads/";
    
    static {
        File uploadsDir = new File(IMAGE_UPLOADS);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
        File truckDir = new File(IMAGE_UPLOADS + "truck");
        if (!truckDir.exists()) {
            truckDir.mkdir();
        }
        File userDir = new File(IMAGE_UPLOADS + "user");
        if (!userDir.exists()) {
            userDir.mkdir();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part imagePart = req.getPart("image");
            
            String entityType = req.getParameter("type");
            int id = -1;
            Visualizable v = null;
            String redirect = null;
            switch (entityType) {
                case "truck":
                    User admin = (User)req.getSession().getAttribute("user");
                    if (admin.getPermissions() != Permissions.ADMIN) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to perform this action.");
                        return;
                    }
                    id = Integer.parseInt(req.getParameter("id"));
                    v = Truck.getTruckByID(id);
                    if (v == null) {
                        // error handling
                        return;
                    }
                    redirect = "truck.jsp?truck=" + id;
                    break;
                case "user":
                    User user = (User)req.getSession().getAttribute("user");
                    if (user == null) {
                        // error handling
                        return;
                    }
                    id = user.getId();
                    v = user;
                    redirect = "profile.jsp?userid=" + id;
                    break;
                default:
                    //error handling
                    return;
            }
            // gets absolute path of the web application
            String appPath = req.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String savePath =  appPath + File.separator + IMAGE_UPLOADS + entityType;

            // creates the save directory if it does not exists
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            String fileName="";
            int i=0;
            for (Part part : req.getParts()) {
                System.out.println(part);
                fileName = id+".png";
                imagePart.write(savePath + File.separator + fileName);
                i++;
                if (i==2)break;
            }
            /*
            BufferedImage image = ImageIO.read(imagePart.getInputStream());
            File output = new File(IMAGE_UPLOADS + entityType + "/" + id + ".png");
            ImageIO.write(image, "png", output);*/
            v.setAvatar(IMAGE_UPLOADS + entityType + File.separator + fileName);
            v.save();
            resp.sendRedirect(redirect);
        } catch (IOException | ServletException | NumberFormatException | ClassCastException ex) {
        }
    }
    
}
