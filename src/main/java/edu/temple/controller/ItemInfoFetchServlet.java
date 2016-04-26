/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemReview;
import java.text.DateFormat;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class ItemInfoFetchServlet extends HttpServlet {
    
    /**
     * Processes a get request for this servlet. This servlet fetches a specified item's reviews and writes them to output as JSON.
     * This servlet takes 1 parameter: criteria, the ID of the item to retrieve reviews for.
     * The output of this request is a JSON array which contains JSON objects. The objects have 4 attributes: text, a String containing the text of the review, stars, the average total score of the truck, date, a String containing the date this review was written, and user, an object containing information about the writer of this review.
     * The user object contains 3 parameters itself: name, the display name of this user, email, the email of this user, and avatar, a link to this user's avatar.
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
           int itemID = Integer.parseInt(req.getParameter("id"));
           Item item = Item.getItemByID(itemID);
           List<ItemReview> reviews = item.loadReviews();
           JsonArray jsArray = new JsonArray();
           for (ItemReview ir : reviews) {
               JsonObject cur = new JsonObject();
               cur.addProperty("text", ir.getReviewText());
               cur.addProperty("stars", ir.getReviewStars());
               cur.addProperty("date", DateFormat.getDateInstance().format(ir.getReviewDate()));
               JsonObject jsUser = new JsonObject();
               jsUser.addProperty("name", ir.getUser().getDisplayName());
               jsUser.addProperty("email", ir.getUser().getUserEmail());
               jsUser.addProperty("avatar", ir.getUser().getAvatar());
               cur.add("user", jsUser);
               jsArray.add(cur);
           }
           Gson gson = new Gson();
           String s = gson.toJson(jsArray);
           resp.getWriter().print(s);
        } catch (Exception e) {
            
        }
    }
}
