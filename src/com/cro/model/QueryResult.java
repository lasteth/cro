package com.cro.model;

import java.util.List;
/**
 * 
 * @author crocustos
 *	��ѯ�����װ
 * @param <T>
 */
public class QueryResult<T> {
	/**
	 * ��ѯ�ۺϼ�¼�б�
	 */
	private List<T> retultlist;
	/**
	 * �ܼ�¼
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
