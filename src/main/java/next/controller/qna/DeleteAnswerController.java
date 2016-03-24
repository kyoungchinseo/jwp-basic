package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		//String questionId = req.getParameter("questionId");
		
		//System.out.println(answer.toString());
		String answerId = req.getParameter("answerId");
		System.out.println(answerId);
		
		AnswerDao answerDao = new AnswerDao();
		
		answerDao.deleteById(Long.parseLong(answerId));
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(mapper.writeValueAsString(Result.ok().toString()));
			
			//out.print(mapper.writeValueAsString(answerDao.findAllByQuestionId(Long.parseLong(questionId))));
		} catch(Exception e) {
			System.out.println(e);
			
		}	

		return null;
	}

}
