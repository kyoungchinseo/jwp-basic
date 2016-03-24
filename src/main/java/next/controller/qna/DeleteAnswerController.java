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

		String answerId = req.getParameter("answerId");
		AnswerDao answerDao = new AnswerDao();
		System.out.println(answerId);
		
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String response = null;
		try {
			answerDao.deleteById(Long.parseLong(answerId));
			response = mapper.writeValueAsString(Result.ok());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String message = e.toString();
			response = mapper.writeValueAsString(Result.fail(message));
		}

		out.print(response);

		return null;
	}

}
