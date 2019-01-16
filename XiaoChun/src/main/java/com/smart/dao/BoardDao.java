package com.smart.dao;

import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.smart.domain.Board;

@Repository
public class BoardDao extends BaseDao<Board> {

	private static final String GET_BOARD_NUM = "select count(f.board_id) from Board f";
	
	public long getBoardNum() {
		Iterator<?> iter = getHibernateTemplate().iterate(GET_BOARD_NUM);
		return (Long) iter.next();
	}
}
