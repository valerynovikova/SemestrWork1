package ru.itis.novikova.servlet.article;

import ru.itis.novikova.dto.ArticleDTO;
import ru.itis.novikova.helper.HTMLHelper;
import ru.itis.novikova.helper.TextHelper;
import ru.itis.novikova.service.ArticleService;
import ru.itis.novikova.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/allArticlesHandler")
public class AllArticlesHandlerServlet extends HttpServlet {

	private ArticleService articleService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		if (title == null){
			List<ArticleDTO> articles = articleService.findAll();
			mapArticles(resp, articles);
			return;
		}
		List<ArticleDTO> articles = articleService.findAllByTitle(title);
		mapArticles(resp, articles);
	}

	private void mapArticles(HttpServletResponse resp, List<ArticleDTO> articles) throws IOException, ServletException {
		articles = articles.stream()
				.map(article -> new ArticleDTO(article.getId(), article.getUserNick(), article.getTitle(),
						TextHelper.editText(article.getText()), article.getPhotoUrl(), article.getData()))
				.collect(Collectors.toList());

		Collections.reverse(articles);

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(HTMLHelper.makeArticleHTML(articles));
	}

	@Override
	public void init() throws ServletException {
		articleService = new ArticleServiceImpl();
	}
}
