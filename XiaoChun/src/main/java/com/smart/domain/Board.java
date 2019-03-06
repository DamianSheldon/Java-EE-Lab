package com.smart.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_board")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Board extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1043379070035575982L;

	@Id
	@Column(name = "board_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardId;
	
	@Column(name = "board_name")
	private String boardName;
	
	@Column(name = "board_desc")
	private String boardDesc;
	
	@Column(name = "topic_num")
	private int topicNum;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "manBoards", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<User>();

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boradId) {
		this.boardId = boradId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardDesc() {
		return boardDesc;
	}

	public void setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
	}

	public int getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
