package ru.itis.novikova.servlet.report;

import ru.itis.novikova.dto.ReportDTO;
import ru.itis.novikova.helper.HTMLHelper;
import ru.itis.novikova.helper.TextHelper;
import ru.itis.novikova.service.ReportService;
import ru.itis.novikova.service.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/allReportsHandler")
public class AllReportsHandlerServlet extends HttpServlet {

    private ReportService reportService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        if (title == null){
            List<ReportDTO> reports = reportService.findAll();
            mapReports(resp, reports);
            return;
        }
        List<ReportDTO> reports = reportService.findAllByTitle(title);
        mapReports(resp, reports);
    }

    private void mapReports(HttpServletResponse resp, List<ReportDTO> reports) throws IOException, ServletException {
        reports = reports.stream()
                .map(recipe -> new ReportDTO(recipe.getId(), recipe.getUserNick(), recipe.getTitle(),
                        TextHelper.editText(recipe.getText()), recipe.getPhotoUrl(), recipe.getData()))
                .collect(Collectors.toList());

        Collections.reverse(reports);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(HTMLHelper.makeReportHTML(reports));
    }


    @Override
    public void init() throws ServletException {
        reportService = new ReportServiceImpl();
    }
}
