package com.hoyatod.dao;

import java.util.Map;

import com.hoyatod.util.JsonPage;

public interface GenericDao {
	public <T> void saveObj(T t);
	public <T> void delObj(T t);
	public <T> void updateObj(T t);
	public <T> Object findObjById(String id);
	public JsonPage communalPagingQuery(int pageIndex, int pageSize, String hql,String hql_count,String key_filed,Map<String, Object> mapCondition);
}
