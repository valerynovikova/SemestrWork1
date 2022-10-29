package ru.itis.novikova.servlet.report;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.helper.CloudinaryHelper;
import ru.itis.novikova.helper.ImageHelper;
import ru.itis.novikova.model.Report;
import ru.itis.novikova.service.ReportService;
import ru.itis.novikova.service.ReportServiceImpl;

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

@WebServlet(urlPatterns = "/addReport")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class AddReportServlet extends HttpServlet {

    private ReportService reportService;

    private Cloudinary cloudinary;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/addReport.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("content");
        UserDTO userDTO = (UserDTO) req.getAttribute("user");
        int userId = userDTO.getId();

        Part part = req.getPart("photo");
        File file = ImageHelper.makeFile(part);
        Map upload = cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", file.getName()));
        String url = (String) upload.get("url");

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

        Report report= new Report(userId, title, text, url, formatForDateNow.format(date));
        reportService.save(report);

        resp.sendRedirect("/addReport");
    }

    @Override
    public void init() throws ServletException {
        reportService = new ReportServiceImpl();
        cloudinary = CloudinaryHelper.getInstance();
    }
}

