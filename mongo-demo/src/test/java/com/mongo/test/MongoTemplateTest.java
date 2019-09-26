package com.mongo.test;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongo.dao.impl.PersonMongoDaoImpl;
import com.mongo.entity.Address;
import com.mongo.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-mvc.xml" })
public class MongoTemplateTest {
	 
	@Autowired
    private PersonMongoDaoImpl personMongo;

    @Test
    public void testMongoTemplate() throws UnsupportedEncodingException {
    	
        personMongo.insertPerson(new Person("xul",24,new Address("武汉","武昌区",24),null));
//    	personMongo.removePerson("M-"+i);
//        personMongo.updatePerson();
        System.out.println(personMongo.findAll());
//        System.out.println(personMongo.findForRequery("wukong"));
    }
    
    public static String getRandomChar() throws UnsupportedEncodingException {
    	Random random = new Random();
		int hightPos = (176 + Math.abs(random.nextInt(39))); 
		int lowPos = (161 + Math.abs(random.nextInt(93)));
		byte[] b = new byte[2];
		b[0] = (new Integer(hightPos).byteValue());
		b[1] = (new Integer(lowPos).byteValue());
        return new String(b, "GBk");//转成中文;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		
		
	}
}
