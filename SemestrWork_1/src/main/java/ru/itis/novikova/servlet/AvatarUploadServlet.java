package ru.itis.novikova.servlet;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.helper.CloudinaryHelper;
import ru.itis.novikova.helper.ImageHelper;
import ru.itis.novikova.service.UserService;
import ru.itis.novikova.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
		maxFileSize = 5 * 1024 * 1024,
		maxRequestSize = 10 * 1024 * 1024
)
public class AvatarUploadServlet extends HttpServlet {
	private Cloudinary cloudinary;

	private UserService service;//обновление аватарки

	@Override
	public void init() throws ServletException {
		cloudinary = CloudinaryHelper.getInstance();
		service = new UserServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = ImageHelper.makeFile(request.getPart("avatar"));
		HttpSession session = request.getSession();//из сессии мы достаем пользователя
		UserDTO user = (UserDTO) session.getAttribute("user");

		String filename = "profilePhoto" + user.getId();

		Map upload = cloudinary.uploader()
				.upload(file, ObjectUtils.asMap("public_id", filename));
		String url = (String) upload.get("url");
		user.setAvatarUrl(url);
		service.updateAvatar(user);
		response.sendRedirect("/pages/profile.ftl");
	}

}
