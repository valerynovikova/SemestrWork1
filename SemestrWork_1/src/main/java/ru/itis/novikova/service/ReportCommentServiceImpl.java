package ru.itis.novikova.service;


import ru.itis.novikova.dto.ReportCommentDTO;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.jdbc.SimpleDataSource;
import ru.itis.novikova.model.ReportComment;
import ru.itis.novikova.repository.ReportCommentRepository;
import ru.itis.novikova.repository.ReportCommentRepositoryJdbcImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ReportCommentServiceImpl implements ReportCommentService {

	private final ReportCommentRepository reportCommentRepository;

	private final UserService userService;

	public ReportCommentServiceImpl() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\Карина\\IdeaProjects\\SemestrWork_1\\src\\main\\resources\\application.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		this.reportCommentRepository = new ReportCommentRepositoryJdbcImpl(new SimpleDataSource(properties));
		this.userService = new UserServiceImpl();
	}

	@Override
	public ReportCommentDTO findById(int id) {
		ReportComment comment = reportCommentRepository.findById(id);
		UserDTO userDTO = userService.findUserById(comment.getUserId());
		return new ReportCommentDTO(comment.getId(), userDTO, comment.getReportId(), comment.getText());
	}

	@Override
	public List<ReportCommentDTO> findAll() {
		List<ReportComment> all = reportCommentRepository.findAll();

		return all.stream().map(reportComment -> new ReportCommentDTO(reportComment.getId(),
						userService.findUserById(reportComment.getUserId()), reportComment.getReportId(), reportComment.getText()))
				.collect(Collectors.toList());
	}

	@Override
	public void save(ReportComment reportComment) {
		reportCommentRepository.save(reportComment);
	}

	@Override
	public List<ReportCommentDTO> findAllByReportId(int id) {
		List<ReportComment> allByReportId = reportCommentRepository.findAllByReportId(id);

		return allByReportId.stream().map(reportComment -> new ReportCommentDTO(reportComment.getId(),
				userService.findUserById(reportComment.getUserId()), reportComment.getReportId(), reportComment.getText()))
				.collect(Collectors.toList());
	}
}
