package com.smart.service;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

import com.smart.domain.Board;
import com.smart.domain.MainPost;
import com.smart.domain.Post;
import com.smart.domain.Topic;
import com.smart.domain.User;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;

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
    @DataSet("XiaoChun.DataSet.xls")
    public void testAddBoard() throws Exception {
        Board board = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_board", Board.class);

        forumService.addBoard(board);

        Board boardInDb = forumService.getBoardById(board.getBoardId());
        assertEquals(boardInDb.getBoardName(), "育儿");
    }

    @Test
    @DataSet("XiaoChun.DataSet.xls")
    public void testAddTopic() throws Exception {
        Topic topic = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_topic", Topic.class);
        User user = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_user", User.class);
        MainPost post = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_post", MainPost.class);

        topic.setUser(user);
        topic.setMainPost(post);
        forumService.addTopic(topic);

        Board boardInDb = forumService.getBoardById(1);
        User userInDb = userService.getUserByUserName("tom");

        assertEquals(boardInDb.getTopicNum(), 1);
        assertEquals(userInDb.getCredit(), 110);
        assertEquals(topic.getTopicId() > 0, true);
    }

    @Test
    @DataSet("XiaoChun.DataSet.xls")
    public void testRemoveTopic() {
        forumService.removeTopic(1);
        Topic topicInDb = forumService.getTopicByTopicId(1);
        assertNull(topicInDb);
    }

    @Test
    @DataSet("XiaoChun.DataSet.xls")
    public void testAddPost() throws Exception {
        Post post = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_post", Post.class);

        User user = XlsDataSetBeanFactory.createBean(ForumServiceTests.class, "XiaoChun.DataSet.xls", "t_user", User.class);
        post.setUser(user);

        Topic topic = new Topic();
        topic.setTopicId(1);
        post.setTopic(topic);

        forumService.addPost(post);

        User userInDb = userService.getUserByUserName("tom");

        Topic topicInDb = forumService.getTopicByTopicId(1);

        assertEquals(post.getPostId() > 1, true);
        assertEquals(userInDb.getCredit(), 105);
        assertEquals(topicInDb.getReplies(), 2);
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
