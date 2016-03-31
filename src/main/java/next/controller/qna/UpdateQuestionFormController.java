package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.User;

public class UpdateQuestionFormController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(UpdateQuestionFormController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("enter update request");
		long questionId = Long.parseLong(request.getParameter("questionId"));
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		// compare writer of questionId and name of user logged in.
		QuestionDao questionDao = new QuestionDao();
		if (questionDao.findById(questionId).getWriter().equals(user.getName())) {
			log.debug("Update can be done.");
			
			ModelAndView mav = jspView("/qna/updateQuestionForm.jsp");
	        mav.addObject("question", questionDao.findById(questionId));
	        return mav;
		} else {
			return jspView("../index.jsp").addObject("questions", questionDao.findAll());
		}
	}

}
