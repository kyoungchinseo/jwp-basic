package next.controller.qna;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class DeleteQuestionController extends AbstractController{

	private static final Logger log = LoggerFactory.getLogger(DeleteQuestionController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("enter delete request");
		log.debug("questionId: {}",request.getParameter("questionId"));
		ServletContext sc = request.getServletContext(); 
		QuestionDao questionDao = (QuestionDao)sc.getAttribute("questionDao");
		AnswerDao answerDao = (AnswerDao)sc.getAttribute("answerDao");
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		// 답변이 없으면 지운다. 
		Question question = questionDao.findById(questionId);
		int countOfComment = question.getCountOfComment();
		if (countOfComment == 0) {
			log.debug("DELETE: no comment");
			questionDao.delete(questionId);
			return jspView("redirect:/");
		} 
		// 질문자와 답변자가 같은 경우 삭제할 수 있다.
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		int count = 0;
		for (Answer answer : answers) {
			if (answer.getWriter().equals(question.getWriter())) {
				count++;
			}
		}
		if (count == answers.size()) {
			questionDao.delete(questionId);
			answerDao.deleteByQuestionId(questionId);
		}
		// 질문자와 답변자가 다른 경우 삭제 할 수 없다.
		return jspView("redirect:/");
		
	}

}
