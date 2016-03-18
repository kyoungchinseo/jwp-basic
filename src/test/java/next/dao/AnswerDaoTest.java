package next.dao;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.Answer;

public class AnswerDaoTest {

	@Before
	public void setUp() throws Exception {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void testInsert() {
//		Calendar calender = Calendar.getInstance();
//		Timestamp  tsTimestamp = new Timestamp(calender.getTime().getTime());
//		System.out.println(tsTimestamp.toString());
//
//		Answer expected = new Answer(6,"dwua","hello, hello",tsTimestamp, 1);
//		AnswerDao answerDao = new AnswerDao();
//		answerDao.insert(expected);
//		System.out.println(expected.toString());
//		
//		Answer returned = answerDao.findByAnswerId(expected.getAnswerId());
//		System.out.println(returned.toString());
//		
//		assertEquals(expected, returned);
		
	}

}
