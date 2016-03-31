package next.support.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.dao.UserDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);
	
	//public static JdbcTemplate jdbcTemplate;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
		
	
		ServletContext sc = sce.getServletContext();
		
		AnswerDao answerDao = new AnswerDao();
		QuestionDao questionDao = new QuestionDao();
		UserDao userDao = new UserDao();
		
		sc.setAttribute("answerDao", answerDao);
		sc.setAttribute("questionDao", questionDao);
		sc.setAttribute("userDao", userDao);
					
		
		logger.info("Completed Load ServletContext!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
