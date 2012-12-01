package com.cro.services;


import java.io.Serializable;
import java.util.LinkedHashMap;

import com.cro.dao.BaseDao;
import com.cro.model.QueryResult;
import com.cro.model.user.Buyer;

public interface BuyerService<T> {

	public abstract void registerBuyer(Buyer buyer);
	
	public void save(T entity);

	public void update(T entity);

	public T find(Serializable entityId);

	public void delete(Serializable... ids);
	
	public QueryResult<T> getScrollData();
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult) ;
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult, LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData(int startIndex, int maxResult, String whereJPQL, Object[] params, LinkedHashMap<String, String> orderby);

	public abstract Buyer findUserByLoginName(String loginName);
}
