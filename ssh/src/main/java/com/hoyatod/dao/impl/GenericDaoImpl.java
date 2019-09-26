package com.hoyatod.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoyatod.dao.GenericDao;
import com.hoyatod.util.JsonPage;

@Repository("genericDao")
@Transactional
public class GenericDaoImpl implements GenericDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public <T> void saveObj(T t) {
		// TODO Auto-generated method stub
		this.getCurrentSession().save(t);
	}

	@Override
	public <T> void delObj(T t) {
		// TODO Auto-generated method stub
		this.getCurrentSession().delete(t);
	}

	@Override
	public <T> void updateObj(T t) {
		// TODO Auto-generated method stub
		this.getCurrentSession().update(t);
	}
	 
	/*@Override
	public <T> Object findObjById(String id) {
		// TODO Auto-generated method stub
		String hql = "from Article where id = '" + id +"'";
		@SuppressWarnings("unchecked")
		List<Article> list = this.getCurrentSession().createQuery(hql).list();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;                                                                                                                    
	}*/
	
	@Override
	public JsonPage communalPagingQuery(int pageIndex, int pageSize, String hql, String hql_count,String key_filed,Map<String, Object> mapCondition) {
		int firstResult = JsonPage.getStartOfPage(pageIndex, pageSize);
		firstResult = firstResult < 0 ? 0 : firstResult;

		StringBuilder conditionBuilder = new StringBuilder();
		if (mapCondition == null)
			mapCondition = new HashMap<String, Object>();

		for (Iterator<String> iterator = mapCondition.keySet().iterator(); iterator.hasNext();) {

			String key = iterator.next();
			Object value = mapCondition.get(key);
			if (key.equals(key_filed))
				if (value != null) {
					if (!value.toString().equals(""))
						conditionBuilder.append(" and " + key + " like '" + "%" + value + "%' ");
				}
		}

		hql_count = hql_count + conditionBuilder.toString();
		int count_sum = this.count(hql_count);
		if (count_sum < 1)
			return new JsonPage();

		conditionBuilder.append(" order by id asc ");
		hql = hql + conditionBuilder.toString();
		Query query = this.getCurrentSession().createQuery(hql);
		query.setFirstResult(firstResult < 0 ? 0 : firstResult);
		if (pageSize > 0)
			query.setMaxResults(pageSize);

		@SuppressWarnings("rawtypes")
		List resultList = query.list();
		return new JsonPage(JsonPage.getStartOfPage(pageIndex, pageSize), count_sum, pageSize, resultList);
	}

	@SuppressWarnings("rawtypes")
	public List list(final String hql, final int offset, final int limit) {
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		if (limit > 0)
			query.setMaxResults(limit);
		List list = query.list();
		return list;
	}

	public int count(String hql) {
		@SuppressWarnings("rawtypes")
		List list = this.getCurrentSession().createQuery(hql).list();
		if (list == null || list.size() == 0)
			return 0;
		Long l = (Long) list.get(0);
		if (l == null)
			return 0;
		return l.intValue();
	}

	@Override
	public <T> Object findObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}










