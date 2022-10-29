package ru.itis.novikova.servlet.report;

import ru.itis.novikova.dto.ReportCommentDTO;
import ru.itis.novikova.dto.ReportDTO;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.model.ReportComment;
import ru.itis.novikova.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/reportInfo")
public class ReportInfoServlet extends HttpServlet {

    private ReportService reportService;

    private UserService userService;

    private ReportCommentService reportCommentService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportDTO report = reportService.findById(Integer.parseInt(req.getParameter("id")));
        UserDTO user = userService.findUserByNick(report.getUserNick());
        List<ReportCommentDTO> comments =
                reportCommentService.findAllByReportId(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("report", report);
        req.setAttribute("u", null);
        req.setAttribute("author", user);
        req.setAttribute("isComments", comments);
        req.setAttribute("comments", comments);
        req.setAttribute("userNow", req.getAttribute("user"));

        req.getRequestDispatcher("/pages/reportInfo.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getAttribute("user");

        String text = req.getParameter("comment");
        int userId = user.getId();

        ReportComment comment = new ReportComment(userId, Integer.parseInt(req.getParameter("id")), text);
        reportCommentService.save(comment);

        String redirect = "/reportInfo?id=" + req.getParameter("id");
        resp.sendRedirect(redirect);
    }

    @Override
    public void init() throws ServletException {
        reportService = new ReportServiceImpl();
        userService = new UserServiceImpl();
        reportCommentService = new ReportCommentServiceImpl();
    }
}

