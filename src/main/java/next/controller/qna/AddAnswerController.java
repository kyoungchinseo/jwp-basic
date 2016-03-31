package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class AddAnswerController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	private AnswerDao answerDao = new AnswerDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(req.getParameter("questionId"));
		
		Answer answer = new Answer(req.getParameter("writer"), 
				req.getParameter("contents"), 
				questionId);
		log.debug("answer : {}", answer);
		
		Answer savedAnswer = answerDao.insert(answer);
		
		QuestionDao questionDao = new QuestionDao();
		questionDao.increaseCountOfAnswer(questionId);
		
		log.debug("questionDao: {}",questionDao.findById(questionId).getCountOfComment());
		
		ModelAndView mav = jsonView();
		mav.addObject("answer", savedAnswer);
		mav.addObject("question", questionDao.findById(questionId));
		mav.addObject("countOfComment", questionDao.findById(questionId).getCountOfComment());
		return mav;
	}
}
