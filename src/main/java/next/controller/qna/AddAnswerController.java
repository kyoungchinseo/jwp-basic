package next.controller.qna;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;

public class AddAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		long questionId = Long.parseLong(req.getParameter("questionId"));
		
		Answer answer = new Answer(
				req.getParameter("writer"),
				req.getParameter("contents"),
				questionId);
		//System.out.println(answer.toString());
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String response = mapper.writeValueAsString(null);
		
		AnswerDao answerDao = new AnswerDao();
		
		try {
			Answer savedAnswer = answerDao.insert(answer);
			response = mapper.writeValueAsString(savedAnswer);
		} catch(Exception e) {
			System.out.println(e);
			String message = e.toString();
			response = mapper.writeValueAsString(Result.fail(message));
		}
		
		
		
		out.print(response);
		
		return null;  // <=======================
	}
	

}
