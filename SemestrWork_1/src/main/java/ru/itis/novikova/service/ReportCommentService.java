package ru.itis.novikova.service;


import ru.itis.novikova.dto.ReportCommentDTO;
import ru.itis.novikova.model.ReportComment;

import java.util.List;

public interface ReportCommentService {
	ReportCommentDTO findById(int id);
	List<ReportCommentDTO> findAll();
	void save(ReportComment reportComment);
	List<ReportCommentDTO> findAllByReportId(int id);
}
