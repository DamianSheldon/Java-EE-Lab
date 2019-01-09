package com.smart.dao;

import java.util.List;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.smart.domain.Topic;
import com.smart.domain.User;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;

public class TopicDaoTests extends BaseDaoTest {

    @SpringBean("topicDao")
    TopicDao topicDao;

    @Test
    @DataSet("XiaoChun.SaveTopics.xls")
    @ExpectedDataSet("XiaoChun.ExpectedTopics.xls")
    public void testAddTopic() throws Exception {
        List<Topic> topics = XlsDataSetBeanFactory.createBeans(TopicDaoTests.class, "XiaoChun.SaveTopics.xls", "t_topic", Topic.class);
        for (Topic topic : topics) {
            User user = new User();
            user.setUserId(1);
            topic.setUser(user);
            topicDao.save(topic);
        }
    }

}
