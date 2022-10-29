package ru.itis.novikova.service;


import ru.itis.novikova.dto.ReportDTO;
import ru.itis.novikova.jdbc.SimpleDataSource;
import ru.itis.novikova.model.Report;
import ru.itis.novikova.repository.ReportRepository;
import ru.itis.novikova.repository.ReportRepositoryJdbcImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;

	private final UserService userService;

	public ReportServiceImpl() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\Карина\\IdeaProjects\\SemestrWork_1\\src\\main\\resources\\application.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		this.reportRepository = new ReportRepositoryJdbcImpl(new SimpleDataSource(properties));
		this.userService = new UserServiceImpl();
	}

	@Override
	public void save(Report report) {
		reportRepository.save(report);
	}

	@Override
	public List<ReportDTO> findAll() {
		List<Report> all = reportRepository.findAll();

		return all.stream().map(report -> new ReportDTO(report.getId(),
				userService.findUserById(report.getUserId()).getNick(), report.getTitle(), report.getText(),
				report.getPhotoUrl(), report.getData())).collect(Collectors.toList());
	}

	@Override
	public List<ReportDTO> findAllByTitle(String title) {
		List<Report> allByTitle = reportRepository.findAllByTitle(title);

		return allByTitle.stream().map(report -> new ReportDTO(report.getId(),
						userService.findUserById(report.getUserId()).getNick(),
						report.getTitle(), report.getText(), report.getPhotoUrl(), report.getData()))
				.collect(Collectors.toList());
	}

	@Override
	public ReportDTO findById(int id) {
		Report reportById = reportRepository.findById(id).get();
		return ReportDTO.builder()
				.data(reportById.getData())
				.id(reportById.getId())
				.photoUrl(reportById.getPhotoUrl())
				.text(reportById.getText())
				.title(reportById.getTitle())
				.userNick(userService.findUserById(reportById.getUserId()).getNick())
				.build();
	}

	@Override
	public List<ReportDTO> findAllByUserId(int id) {
		List<Report> allByUserId = reportRepository.findAllByUserId(id);

		return allByUserId.stream()
				.map(report -> new ReportDTO(report.getId(), userService.findUserById(report.getUserId()).getNick(),
						report.getTitle(), report.getText(), report.getPhotoUrl(), report.getData()))
				.collect(Collectors.toList());
	}

}
