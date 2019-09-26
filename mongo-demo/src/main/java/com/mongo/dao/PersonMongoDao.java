package com.mongo.dao;

import java.util.List;

import com.mongo.entity.Person;

public interface PersonMongoDao {
	public List<Person> findAll();
	public void insertPerson(Person user);
	public void removePerson(String userName);
	public void updatePerson();
	public List<Person> findForRequery(String userName);
}
