package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionDao;

public class HomeController implements Controller {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		req.setAttribute("questions", questionDao.findAll());
		return "index.jsp";
	}
}
