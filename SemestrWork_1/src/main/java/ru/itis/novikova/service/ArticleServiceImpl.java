package ru.itis.novikova.service;

import ru.itis.novikova.dto.ArticleDTO;
import ru.itis.novikova.jdbc.SimpleDataSource;
import ru.itis.novikova.model.Article;
import ru.itis.novikova.repository.ArticleRepository;
import ru.itis.novikova.repository.ArticleRepositoryJdbcImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;

	private final UserService userService;

	public ArticleServiceImpl() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\Карина\\IdeaProjects\\SemestrWork_1\\src\\main\\resources\\application.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		this.articleRepository = new ArticleRepositoryJdbcImpl(new SimpleDataSource(properties));
		this.userService = new UserServiceImpl();
	}

	@Override
	public void save(Article article) {
		articleRepository.save(article);

	}

	@Override
	public List<ArticleDTO> findAll() {
		List<Article> all = articleRepository.findAll();

		return all.stream().map(build -> new ArticleDTO(build.getId(),
				userService.findUserById(build.getUserId()).getNick(), build.getTitle(), build.getText(),
				build.getPhotoUrl(), build.getData())).collect(Collectors.toList());
	}

	@Override
	public List<ArticleDTO> findAllByTitle(String title) {
		List<Article> allByTitle = articleRepository.findAllByTitle(title);

		return allByTitle.stream().map(article -> new ArticleDTO(article.getId(),
						userService.findUserById(article.getUserId()).getNick(),
						article.getTitle(), article.getText(), article.getPhotoUrl(), article.getData()))
				.collect(Collectors.toList());
	}

	@Override
	public ArticleDTO findById(int id) {
		Article reportById = articleRepository.findById(id).get();
		return ArticleDTO.builder()
				.data(reportById.getData())
				.id(reportById.getId())
				.photoUrl(reportById.getPhotoUrl())
				.text(reportById.getText())
				.title(reportById.getTitle())
				.userNick(userService.findUserById(reportById.getUserId()).getNick())
				.build();
	}

	@Override
	public List<ArticleDTO> findAllByUserId(int id) {
		List<Article> allByUserId = articleRepository.findAllByUserId(id);

		return allByUserId.stream()
				.map(article -> new ArticleDTO(article.getId(), userService.findUserById(article.getUserId()).getNick(),
						article.getTitle(), article.getText(), article.getPhotoUrl(), article.getData()))
				.collect(Collectors.toList());
	}
}
