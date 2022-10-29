package ru.itis.novikova.service;


import ru.itis.novikova.dto.ArticleDTO;
import ru.itis.novikova.model.Article;

import java.util.List;

public interface ArticleService {
	void save(Article article);
	List<ArticleDTO> findAll();
	List<ArticleDTO> findAllByTitle(String title);
	ArticleDTO findById(int id);
	List<ArticleDTO> findAllByUserId(int id);
}
