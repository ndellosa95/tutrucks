/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nickdellosa
 */
public class DBUtilTest {
    
    public DBUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void searchTest() {
        String searchTerms = "chicken";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query q = session.createQuery(
                "from Truck, Item, Tag where lower(Truck.truckName) like '%" + searchTerms + "%' or " +
                "lower(Item.itemName) like '%" + searchTerms + "%' or lower(Tag.tagName) like '%" + searchTerms + "%'"
        );
        for (Object o : q.list()) {
            System.out.println(((Truck)o).getTruckName());
        }
        session.close();
    }
}
