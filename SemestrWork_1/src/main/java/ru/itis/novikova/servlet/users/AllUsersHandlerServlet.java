package ru.itis.novikova.servlet.users;


import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.helper.HTMLHelper;
import ru.itis.novikova.service.UserService;
import ru.itis.novikova.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/allUsersHandler")
public class AllUsersHandlerServlet extends HttpServlet {

	private UserService userService;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("nickname");
		if (nickname == null){
			List<UserDTO> users = userService.findAll();
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(HTMLHelper.makeUserHTML(users));
			return;
		}

		UserDTO user = userService.findUserByNick(nickname);
		List<UserDTO> users = new ArrayList<>();

		if (!(user == null)){
			users.add(user);
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(HTMLHelper.makeUserHTML(users));
	}

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}
}
