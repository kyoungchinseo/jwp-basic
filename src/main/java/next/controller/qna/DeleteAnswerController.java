package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    
	private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);
	
	private AnswerDao answerDao= new AnswerDao();
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long answerId = Long.parseLong(request.getParameter("answerId"));
		
		ModelAndView mav = jsonView();
		try {
			QuestionDao questionDao = new QuestionDao();
			questionDao.decreaseCountOfAnswer(answerDao.findById(answerId).getQuestionId());
			
			answerDao.delete(answerId);
			
			mav.addObject("result", Result.ok());
		} catch (DataAccessException e) {
			mav.addObject("result", Result.fail(e.getMessage()));
		}
		return mav;
	}
}
