package com.hoyatod.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonPage   implements Serializable {
	
	@Override
	public String toString() {
		return "JsonPage [pageSize=" + pageSize + ", start=" + start + ", data=" + data + ", totalCount=" + totalCount
				+ "]";
	}


	private static int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE; 

	private long start; 

	private List<?> data; 

	private long totalCount; 
	
	@SuppressWarnings("rawtypes")
	public JsonPage() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}
	
	@SuppressWarnings("rawtypes")
	public JsonPage(long start, long totalSize, int pageSize, List data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public List<?> getResult() {
		return data;
	}
	
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}
	
	public boolean isHasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}
	
	public boolean isHasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
	
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	
	private static final long serialVersionUID = -8238530548127081424L;
	
	
}
