/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
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
public class TagTest {
    
    public TagTest() {
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
    public void testSearchTags() {
        String terms = "halal";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Tag where tagName like '%" + terms + "%'"
        );
        List l = q.list();
        session.close();
        ArrayList<Tag> testResults = new ArrayList<>(l.size());
        for (Searchable s : Searchable.SearchOrganizer.organize(l, terms)) testResults.add((Tag)s);
        List<Tag> results = Tag.searchTags(terms);
        for (int i=0; i < results.size(); i++) {
            assertEquals(testResults.get(i).getSearchName(), results.get(i).getSearchName());
        }
    }
}
