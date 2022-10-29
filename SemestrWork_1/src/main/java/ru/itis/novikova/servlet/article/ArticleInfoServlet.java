package ru.itis.novikova.servlet.article;


import ru.itis.novikova.dto.ArticleCommentDTO;
import ru.itis.novikova.dto.ArticleDTO;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.model.ArticleComment;
import ru.itis.novikova.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/articleInfo")
public class ArticleInfoServlet extends HttpServlet {

	private ArticleService articleService;

	private UserService userService;

	private ArticleCommentService articleCommentService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDTO userNow = (UserDTO) req.getAttribute("user");
		ArticleDTO article = articleService.findById(Integer.parseInt(req.getParameter("id")));
		UserDTO user = userService.findUserByNick(article.getUserNick());
		List<ArticleCommentDTO> comments =
				articleCommentService.findAllByArticleId(Integer.parseInt(req.getParameter("id")));

		req.setAttribute("article", article);
		req.setAttribute("u", null);
		req.setAttribute("author", user);
		req.setAttribute("isComments", comments);
		req.setAttribute("comments", comments);
		req.setAttribute("userNow", userNow);

		req.getRequestDispatcher("/pages/articleInfo.ftl").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDTO user = (UserDTO) req.getAttribute("user");

		String text = req.getParameter("comment");
		int userId = user.getId();

		ArticleComment comment = new ArticleComment(userId, Integer.parseInt(req.getParameter("id")), text);
		articleCommentService.save(comment);

		String redirect = "/articleInfo?id=" + req.getParameter("id");
		resp.sendRedirect(redirect);
	}

	@Override
	public void init() throws ServletException {
		articleService = new ArticleServiceImpl();
		userService = new UserServiceImpl();
		articleCommentService = new ArticleCommentServiceImpl();
	}
}
