package com.cro.model;

import java.util.List;
/**
 * 
 * @author crocustos
 *	查询结果封装
 * @param <T>
 */
public class QueryResult<T> {
	/**
	 * 查询综合记录列表
	 */
	private List<T> retultlist;
	/**
	 * 总记录
	 */
	private long totalrecord;
	public List<T> getRetultlist() {
		return retultlist;
	}
	public void setRetultlist(List<T> retultlist) {
		this.retultlist = retultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	
	
}
