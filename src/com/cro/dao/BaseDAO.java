package com.cro.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.cro.model.QueryResult;

/**
 * 
 * @author crocustos  µÃÂ∏®÷˙¿‡
 * @param <T>
 */
public interface BaseDAO<T> {
	public void save(T entity);

	public void update(T entity);

	public T find(Serializable entityId);

	public void delete(Serializable... ids);
	
	public QueryResult<T> getScrollData();
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult) ;
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult, LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData(int startIndex, int maxResult, String whereJPQL, Object[] params, LinkedHashMap<String, String> orderby);
}
