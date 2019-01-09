package com.smart.dao;

import java.util.List;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.smart.domain.Board;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;

public class BoardDaoTests extends BaseDaoTest {

	@SpringBean("boardDao")
	private BoardDao boardDao;
	
	@Test
	@DataSet(value = "XiaoChun.SaveBoards.xls")
    @ExpectedDataSet("XiaoChun.ExpectedBoards.xls")
	public void testAddBoard() throws Exception {
        List<Board> boards = XlsDataSetBeanFactory.createBeans(BoardDaoTests.class, "XiaoChun.SaveBoards.xls", "t_board", Board.class);

        for (Board board : boards) {
            boardDao.update(board);
        }
		
	}
}
