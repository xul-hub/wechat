package com.hoyatod.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public class CommonDAO<T> {
	
	//获取某一个类的所有数据
	//泛型的问题，为什么这里不能使用T.getClass?,可能就跟不能直接new T()是一样的道理,
	//需要运行时候才知道
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(Class<T> clazz){
		List<T> lists = (List<T>)this.getCurrentSession().createQuery("from " + clazz.getClass().getName());
		return lists;
	}
	
	//保存指定实体类
	public void save(T t){
		this.getCurrentSession().save(t);
	}
	
	//删除指定实体类
	public void delete(T t){
		this.getCurrentSession().delete(t);
	}
	
	//更新或者保存指定实体
	public void saveOrUpdate(T t){
		this.getCurrentSession().saveOrUpdate(t);
	}
	
	//根据id查找指定实体对象
	@SuppressWarnings("unchecked")
	public T findById(Class cla,int id){
		return (T)this.getCurrentSession().get(cla, id);
	}
	
/*	//查询指定hql并返回集合
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values){
		return (List<T>)this.getCurrentSession().create.find(hql, values);
	}*/
	
	//查询指定实体的总记录数
	/*public int getCount(Class<T> clazz){
		int count =  DataAccessUtils.intResult(getCurrentSession().createQuery("select count(*) from " + clazz.getName()).list().get(0));
		return count;
	}*/
	
	public int count(String hql){
		List list = this.getCurrentSession().createQuery(hql).list();
		if(list == null || list.size() == 0) return 0;
		Long l = (Long)list.get(0);
		if(l == null) return 0;
		return l.intValue();
	}
	public int count(boolean isHql, String sql) {
		if (isHql) {
			return this.count(sql);
		}
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		return ((BigInteger)query.uniqueResult()).intValue();
	}
	//根据传入的偏移量和步长来查询数据
	@SuppressWarnings("unchecked")
	public List<T> list(final String hql, final int offset, final int limit){
				Query query = getCurrentSession().createQuery(hql);
				query.setFirstResult(offset);
				if(limit > 0)
					query.setMaxResults(limit);
				List list = query.list();
				return list;
	}
	@SuppressWarnings("unchecked")
	public List<T> list(boolean isHql,final String hql, final int offset, final int limit){
		if(isHql){
			return this.list(hql, offset, limit);
		}
				Query query = getCurrentSession().createSQLQuery(hql);
				query.setFirstResult(offset);
				if(limit > 0)
					query.setMaxResults(limit);
				List list = query.list();
				return list;
	}
	//根据传入的偏移量和步长来查询数据
	@SuppressWarnings("unchecked")
	public int executeUpdateOrDelete(final String hql){
		Query query = getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	/*public static CommonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CommonDAO) ctx.getBean("CommonDAO");
	}*/
	
	public  T lockByProperty(String property, Object value, Class<T> clazz) {
		Criteria c = this.getCurrentSession().createCriteria(clazz);
		c.setLockMode(LockMode.PESSIMISTIC_WRITE);
		c.add(Restrictions.eq(property, value));
		List<T> lists = (List<T>)c.list();
		if (lists != null && lists.size() > 0) {
			return lists.get(0);
		}
		return null;
	}
}
