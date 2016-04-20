/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import edu.temple.tutrucks.DBUtils;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.Searchable;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nickdellosa
 */
public class AutoCompleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("criteria");
        int numResults = req.getParameter("numResults") == null ? -1 : Integer.parseInt(req.getParameter("numResults"));
        boolean subs = req.getParameter("subscripts") == null ? false : Boolean.parseBoolean(req.getParameter("subscripts"));
        List<Searchable> results = DBUtils.searchAll(search, null);
        JsonArray tbp = new JsonArray();
        if (numResults < 0) {
            numResults = results.size();
        }

        for (int i = 0; i < numResults; i++) {
            String sn = results.get(i).getSearchName();
            String html = "<span class='ac_subtext'>*</span>";
            if (subs) {
                sn += html.replace("*", results.get(i) instanceof Item
                        ? "at " + (((Item) results.get(i)).getMenu().getTruck().getTruckName())
                        : results.get(i).getClass().getSimpleName());
            }
            tbp.add(sn);
        }
        Gson gson = new Gson();
        String s = gson.toJson(tbp);
        resp.getWriter().print(s);
    }
}
