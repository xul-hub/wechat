package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongo.dao.PersonMongoDao;
import com.mongo.entity.Person;

@Repository("personMongoImpl")
public class PersonMongoDaoImpl implements PersonMongoDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Person.class, "person");
	}

	@Override
	public void insertPerson(Person person) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(person, "person");
	}

	@Override
	public void removePerson(String userName) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(Query.query(Criteria.where("name").is(userName)),"person");
	}

	@Override
	public void updatePerson() {
		// TODO Auto-generated method stub
		mongoTemplate.updateMulti(Query.query(Criteria.where("age").gt(3).lte(5)), Update.update("age",3),"person");
	}

	@Override
	public List<Person> findForRequery(String userName) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(Query.query(Criteria.where("name").is(userName)),Person.class);
	}

}
