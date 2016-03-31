package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.model.User;

public class CreateQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = null;
		user = (User) session.getAttribute("user");
		
		if (user != null) {
			return jspView("/qna/form.jsp");
		} else { // not loggedin
 			return jspView("redirect:/");
		}
	}

}
