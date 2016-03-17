package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;
import next.dao.UserDao;

public class HomeController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//req.setAttribute("users", DataBase.findAll());

		UserDao userDao = new UserDao();
		req.setAttribute("users",userDao.findAll());
		
		
		return "index.jsp";
	}
}
