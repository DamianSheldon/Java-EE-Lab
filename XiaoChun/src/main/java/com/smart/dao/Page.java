package com.smart.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4693467800270899446L;

	private static int DEFAULT_PAGE_SIZE = 20;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private long start;
	
	private long totalCount;
	
	private List data;
	
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}
	
	public Page(long start, long totalSize, int pageSize, List data) {
		this.start = start;
		this.totalCount = totalSize;
		this.pageSize = pageSize;
		this.data = data;
	}

    public long getTotalCount() {
        return this.totalCount;
    }

	public long getTotalPageCount() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		}
		else {
			return totalCount / pageSize + 1;
		}
	}

    public int getPageSize() {
        return pageSize;
    }

    public List getResult() {
        return data;
    }

	public long getCurrentPageNo () {
		return start / pageSize + 1;
	}
	
	public boolean isHasNextPage() {
		return getCurrentPageNo() < getTotalPageCount();
	}
	
	public boolean isHasPreviousPage() {
		return getCurrentPageNo() > 1;
	}
	
	public static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
	
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
}
