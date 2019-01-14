package com.smart.web.controller;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.smart.domain.Board;
import com.smart.web.ForumManagerController;

public class ForumManagerControllerTests extends BaseWebTest {
    @SpringBeanByType
    private ForumManagerController controller;

    @Test
    public void listAllBoards() throws Exception {
        request.setRequestURI("/index");
        request.setMethod("GET");

        ModelAndView mav = controller.listAllBoards();
        List<Board> boards = (List<Board>) mav.getModelMap().get("boards");

        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/listAllBoards");
        assertNotNull(boards);
    }

    @Test
    public void addBoardPage() throws Exception {
        request.setRequestURI("/forum/addBoardPage");
        request.setMethod("GET");

        String viewName = controller.addBoardPage();

        assertEquals(viewName, "/addBoard");
    }

    @Test
    public void addBoard() throws Exception {
    
    }

    @Test
    public void setBoardManagerPage() throws Exception {
    
    }

    @Test
    public void setBoardManager() throws Exception {
    
    }

    @Test
    public void userLockManagePage() throws Exception {
    
    }

    @Test
    public void userLockManage() throws Exception {
    
    }

}
