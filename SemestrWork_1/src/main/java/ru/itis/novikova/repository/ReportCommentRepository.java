package ru.itis.novikova.repository;

import ru.itis.novikova.model.ReportComment;

import java.util.List;

public interface ReportCommentRepository {
    ReportComment findById(int id);
    List<ReportComment> findAll();
    void save(ReportComment reportComment);
    List<ReportComment> findAllByReportId(int id);
}
