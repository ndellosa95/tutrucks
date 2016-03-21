/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nickdellosa
 */
public class DBUtils {

    public static List<Searchable> searchAll(String terms) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from edu.temple.tutrucks.Searchable s where ("
                + "s.id in (select tr.id from Truck tr where tr.truckName like '%" + terms + "%') or "
                + "s.id in (select it.id from Item it where it.itemName like '%" + terms + "%') or "
                + "s.id in (select ta.id from Tag ta where ta.tagName like '%" + terms + "%'))"
        );
        List l = q.list();
        session.close();
        List<Searchable> results = Searchable.SearchOrganizer.organize(l, terms); // doesn't work properly
        return results;
    }
}
