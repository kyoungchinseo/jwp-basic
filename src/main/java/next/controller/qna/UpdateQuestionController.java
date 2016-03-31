package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

public class UpdateQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(UpdateQuestionController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("enter update question request");
		long questionId = Long.parseLong(request.getParameter("questionId"));
		log.debug("questionId: {}",questionId);
		log.debug(request.getParameter("modify"));
		log.debug(request.getParameter("title"));
		log.debug(request.getParameter("contents"));
		log.debug(request.getParameter("writer"));
		QuestionDao questionDao = new QuestionDao();

		Question question = new Question(request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("contents"));
		
		questionDao.update(questionId, question);
		
		AnswerDao answerDao = new AnswerDao();
		
		Question updatedQuestion = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		
		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", updatedQuestion);
		mav.addObject("answers", answers);
		mav.addObject("countOfComment", question.getCountOfComment());
		
		return mav;
	}

}
