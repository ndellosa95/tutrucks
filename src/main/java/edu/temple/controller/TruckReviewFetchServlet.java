/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.Review;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.TruckReview;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nickdellosa
 */
public class TruckReviewFetchServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int truckID = Integer.parseInt(req.getParameter("criteria"));
            int minReview = Integer.parseInt(req.getParameter("start"));
            int maxReview = Integer.parseInt(req.getParameter("end"));
            Truck t = Truck.getTruckByID(truckID);
            List<TruckReview> reviews = t.loadReviews().subList(minReview, maxReview);
            JsonArray array = new JsonArray();
            for (TruckReview rev : reviews) {
                JsonObject revObj = new JsonObject();
                revObj.addProperty("text", rev.getReviewText());
                revObj.addProperty("stars", rev.getReviewStars());
                revObj.addProperty("date", rev.getReviewDate().toString());
                JsonObject userInfo = new JsonObject();
                userInfo.addProperty("name", rev.getUser().getDisplayName());
                userInfo.addProperty("email", rev.getUser().getUserEmail());
                userInfo.addProperty("avatar", rev.getUser().getAvatar());
                revObj.add("userinfo", userInfo);
                array.add(revObj);
            }
            Gson gson = new Gson();
            String s = gson.toJson(array);
            resp.getWriter().print(s);
        } catch (Exception e) {
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        
    }
}
