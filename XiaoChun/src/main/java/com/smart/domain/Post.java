package com.smart.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Post extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3879977062561462196L;

	@Column(name = "post_id")
	private int postId;
	
	@Column(name = "post_title")
	private String postTitle;
	
	@Column(name = "post_text")
	private String postText;
	
	@Column(name = "board_id")
	private int boardId;
	
	@Column(name = "create_date")
	private Date createDate;
	
	// TODO:  User
	
	
}
