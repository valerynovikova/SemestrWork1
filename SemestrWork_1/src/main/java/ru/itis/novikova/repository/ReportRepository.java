package ru.itis.novikova.repository;

import ru.itis.novikova.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    void save(Report report);
    List<Report> findAll();
    Optional<Report> findById(int id);
    List<Report> findAllByUserId(int userId);
    List<Report> findAllByTitle(String title);
    void delete(int id);
}

