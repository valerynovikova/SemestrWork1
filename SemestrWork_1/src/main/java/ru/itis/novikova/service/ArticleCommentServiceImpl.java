package ru.itis.novikova.service;


import ru.itis.novikova.dto.ArticleCommentDTO;
import ru.itis.novikova.dto.UserDTO;
import ru.itis.novikova.jdbc.SimpleDataSource;
import ru.itis.novikova.model.ArticleComment;
import ru.itis.novikova.repository.ArticleCommentRepository;
import ru.itis.novikova.repository.ArticleCommentRepositoryJdbcImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ArticleCommentServiceImpl implements ArticleCommentService {

	private final ArticleCommentRepository articleCommentRepository;

	private final UserService userService;

	public ArticleCommentServiceImpl() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\Карина\\IdeaProjects\\SemestrWork_1\\src\\main\\resources\\application.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		this.articleCommentRepository = new ArticleCommentRepositoryJdbcImpl(new SimpleDataSource(properties));
		this.userService = new UserServiceImpl();
	}

	@Override
	public ArticleCommentDTO findById(int id) {
		ArticleComment comment = articleCommentRepository.findById(id);
		UserDTO userDTO = userService.findUserById(comment.getUserId());
		return new ArticleCommentDTO(comment.getId(), userDTO, comment.getArticleId(), comment.getText());
	}

	@Override
	public List<ArticleCommentDTO> findAll() {
		List<ArticleComment> all = articleCommentRepository.findAll();

		return all.stream().map(articleComment -> new ArticleCommentDTO(articleComment.getId(),
						userService.findUserById(articleComment.getUserId()), articleComment.getArticleId(), articleComment.getText()))
				.collect(Collectors.toList());
	}

	@Override
	public void save(ArticleComment articleComment) {
		articleCommentRepository.save(articleComment);

	}

	@Override
	public List<ArticleCommentDTO> findAllByArticleId(int id) {
		List<ArticleComment> allByReportId = articleCommentRepository.findAllByReportId(id);

		return allByReportId.stream().map(articleComment -> new ArticleCommentDTO(articleComment.getId(),
						userService.findUserById(articleComment.getUserId()), articleComment.getArticleId(), articleComment.getText()))
				.collect(Collectors.toList());
	}
}
