package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ShowController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		
		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		
		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		mav.addObject("countOfComment", question.getCountOfComment());
		return mav;
	}
}
