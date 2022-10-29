package ru.itis.novikova.service;

import ru.itis.novikova.dto.ReportDTO;
import ru.itis.novikova.model.Report;

import java.util.List;


public interface ReportService {
	void save(Report report);
	List<ReportDTO> findAll();
	List<ReportDTO> findAllByTitle(String title);
	ReportDTO findById(int id);
	List<ReportDTO> findAllByUserId(int id);
}
