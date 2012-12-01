package com.cro.dao.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.cro.dao.BaseDao;
import com.cro.model.QueryResult;
import com.cro.utils.GenericsUtils;

@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	@PersistenceContext protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void delete(Serializable... ids) {
		for(Serializable id : ids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}
	
	public T find(Serializable entityId) {
		return em.find(entityClass, entityId);
	}
	
	public void save(T entity) {
		em.persist(entity);
	}
	
	public void update(T entity) {
		em.merge(entity);
	}
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult, String whereJPQL, Object[] params, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityName = getEntityName(this.entityClass);
		String where = (whereJPQL != null && !"".equals(whereJPQL.trim())) ? "where " + whereJPQL : "";
		Query query = em.createQuery("select o from "+ entityName + " o " + where + bulidOrderBy(orderby));
		setParameters(query, params);
		if(startIndex != -1 && maxResult != -1) {
			query.setFirstResult(startIndex).setMaxResults(maxResult);
		}
		qr.setRetultlist(query.getResultList());
		query = em.createQuery("select count(o) from "+ entityName + " o " + where);
		setParameters(query, params);
		qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
	}
	
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1, null, null, null);
	}
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult) {
		return getScrollData(startIndex, maxResult, null, null, null);
	}
	
	public QueryResult<T> getScrollData(int startIndex, int maxResult, LinkedHashMap<String, String> orderby) {
		return getScrollData(startIndex, maxResult, null, null, orderby);
	}
	
	/**
	 * 为Query对象设置查询参数
	 * @param query 查询对象
	 * @param params
	 */
	private void setParameters(Query query, Object[] params) {
		if(params != null) {
			for(int i=0; i<params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			
		}
		
	}

	/**
	 * 构建排序语句
	 * @param orderby
	 * @return
	 */
	private String bulidOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuilder sb = new StringBuilder();
		if(orderby != null && orderby.size() > 0) {
			sb.append("order by ");
			for(String key : orderby.keySet()) {
				sb.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

	/**
	 * 获取实体名称
	 * @param entityClass 实体的class对象
	 * @return
	 */
	private static <E> String getEntityName(Class<E> entityClass) {
		String entityname = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name() != null && !"".equalsIgnoreCase(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}
}
