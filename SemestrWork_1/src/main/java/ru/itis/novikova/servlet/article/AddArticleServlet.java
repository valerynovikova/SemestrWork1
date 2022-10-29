package ru.itis.novikova.servlet.article;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.helper.CloudinaryHelper;
import ru.itis.novikova.helper.ImageHelper;
import ru.itis.novikova.model.Article;
import ru.itis.novikova.service.ArticleService;
import ru.itis.novikova.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@MultipartConfig(maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024)
@WebServlet(urlPatterns = "/addArticle")
public class AddArticleServlet extends HttpServlet {

    private Cloudinary cloudinary;
    private ArticleService articleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/pages/addArticle.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getAttribute("user");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int userId = user.getId();
        Part part = req.getPart("photo");

        File file = ImageHelper.makeFile(part);
        Map upload = cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", file.getName()));

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

        Article article = new Article(userId, title, content, (String) upload.get("url"), formatForDateNow.format(date));
        articleService.save(article);

        resp.sendRedirect("/allArticles");
    }

    @Override
    public void init() throws ServletException {
        cloudinary = CloudinaryHelper.getInstance();
        articleService = new ArticleServiceImpl();
    }
}

