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
            
            List<TruckReview> reviews1 = t.loadReviews();
            String s;
            if (minReview>=reviews1.size()){
                minReview=0;
                maxReview=-1;
            };
            if (maxReview>=reviews1.size()) maxReview=reviews1.size()-1;
            List<TruckReview> reviews = reviews1.subList(minReview, maxReview-minReview+1);
            
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
            s = gson.toJson(array);
            System.out.println(s);
            resp.getWriter().print(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        
    }
}
