package next.controller.qna;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class ListQuestionController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Question List");
		
		QuestionDao questionDao = new QuestionDao();
		List<Question> questions = questionDao.findAll();
				
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(questions.toString());
		
		return null;
	}

}
