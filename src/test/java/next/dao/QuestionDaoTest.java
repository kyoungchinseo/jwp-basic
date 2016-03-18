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
import next.model.Question;

public class QuestionDaoTest {

	@Before
	public void setUp() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void test() throws Exception {
		Calendar calender = Calendar.getInstance();
		Timestamp  tsTimestamp = new Timestamp(calender.getTime().getTime());
		System.out.println(tsTimestamp.toString());
		Question expected = new Question(9,"hello","helo world","hello friend. welcome to question database.",
				tsTimestamp,0);
		System.out.println(expected.toString());
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(expected);
		System.out.println(expected.getQuestionId());
		
		Question returned = questionDao.fingByQuestionId(expected.getQuestionId());
		
		System.out.println(returned.toString());
		
		assertEquals(expected, returned);
		
		
	}

}
