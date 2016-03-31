package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class AddQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);
	

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("receive add Question");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//QuestionDao questionDao = new QuestionDao();
		Question question = new Question(user.getName(),
				request.getParameter("title"),request.getParameter("contents"));
		log.debug("Question:{} ", question);
		
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(question);
		
		return jspView("../index.jsp").addObject("questions", questionDao.findAll());
		//return jspView("redirect:/qna/show").addObject("questions", attributeValue)
	}

}
