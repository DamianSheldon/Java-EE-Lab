package com.smart.web.controller;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.smart.domain.Board;
import com.smart.domain.User;
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
        request.setRequestURI("/forum/addBoard");
        request.setMethod("POST");

        Board board = new Board();
        board.setBoardName("SpringMVC");
        board.setBoardDesc("SpringMVC experiences~~");

        String viewName = controller.addBoard(board);

        assertEquals(viewName, "/addBoardSuccess");
    }

    @Test
    public void setBoardManagerPage() throws Exception {
        request.setRequestURI("/forum/setBoardManagerPage");
        request.setMethod("GET");

        ModelAndView mav = controller.setBoardManagerPage();
        List<Board> boards = (List<Board>) mav.getModelMap().get("boards");
        List<User> users = (List<User>) mav.getModelMap().get("users");

        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/setBoardManager");

        assertNotNull(boards);
        assertNotNull(users);
    }

    @Test
    public void setBoardManager() throws Exception {
        request.setRequestURI("/forum/setBoardManager");
        request.setMethod("POST");

        ModelAndView mav = controller.setBoardManager("tom", "1");

        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/success");
    }

    @Test
    public void userLockManagePage() throws Exception {
        request.setRequestURI("/forum/userLockManagePage");
        request.setMethod("GET");

        ModelAndView mav = controller.userLockManagePage();
        List<User> users = (List<User>) mav.getModelMap().get("users");

        assertNotNull(mav);
        assertNotNull(users);
        assertEquals(mav.getViewName(), "/userLockManage");
    }

    @Test
    public void userLockManage() throws Exception {
        request.setRequestURI("/forum/userLockManage");
        request.setParameter("locked", "1");
        request.setParameter("userName", "tom");
        request.setMethod("POST");

        ModelAndView mav = controller.userLockManage("tom", "1");

        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/success");
    }

}
