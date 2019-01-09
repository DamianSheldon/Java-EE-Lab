package com.smart.dao;

import org.springframework.stereotype.Repository;

import com.smart.domain.Topic;

@Repository
public class TopicDao extends BaseDao<Topic> {

	private static final String GET_BOARD_DIGEST_TOPIC = "from Topic t where t.boardId = ? and digest > 0 "
			+ "order by t.lastPost desc, digest desc";
	private static final String GET_PAGED_TOPIC = "from Topic where boardId = ? order by lastPost desc";
	private static final String QUERY_TOPIC_BY_TITLE = "from Topic where topicTitle like ? order by lastPost desc";
	
	public Page getBoardDigestTopics(int boardId, int pageNo, int pageSize) {
		return pagedQuery(GET_BOARD_DIGEST_TOPIC, pageNo, pageSize, boardId);
	}
	
	public Page getPagedTopics(int boardId, int pageNo, int pageSize) {
		return pagedQuery(GET_PAGED_TOPIC, pageNo, pageSize, boardId);
	}
	
	public Page queryTopicByTitle(String title, int pageNo, int pageSize) {
		return pagedQuery(QUERY_TOPIC_BY_TITLE, pageNo, pageSize, title);
	}
	
}
