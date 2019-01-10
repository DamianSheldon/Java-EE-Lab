package com.smart.service;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBean;

public class ForumServiceTests extends BaseServiceTest {
    @SpringBean("forumService")
    private ForumService forumService;

    @SpringBean("userService")
    private UserService userService;

    @BeforeMethod
    public void init() {
        SessionFactory sf = hibernateTemplate.getSessionFactory();
        Map<String, CollectionMetadata> roleMap = sf.getAllCollectionMetadata();
        for (String roleName : roleMap.keySet()) {
            sf.evictCollection(roleName);
        }

        Map<String, ClassMetadata> entityMap = sf.getAllClassMetadata();
        for (String entityName : entityMap.keySet()) {
            sf.evictEntity(entityName);
        }

        sf.evictQueries();
    }

    @Test
    public void testAddBoard() throws Exception {
    
    }

    @Test
    public void testAddTopic() throws Exception {
    
    }

    @Test
    public void testRemoveTopic() {
    
    }

    @Test
    public void testAddPost() throws Exception {
    
    }

    @Test
    public void testRemovePost() {
    
    }

    @Test
    public void testMakeDigestTopic() throws Exception {
    
    }

    @Test
    public void testAddBoardManager() {
    
    }

}
