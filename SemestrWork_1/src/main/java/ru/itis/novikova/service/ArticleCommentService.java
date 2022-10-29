package ru.itis.novikova.service;

import ru.itis.novikova.dto.ArticleCommentDTO;
import ru.itis.novikova.model.ArticleComment;

import java.util.List;

public interface ArticleCommentService {
	ArticleCommentDTO findById(int id);
	List<ArticleCommentDTO> findAll();
	void save(ArticleComment articleComment);
	List<ArticleCommentDTO> findAllByArticleId(int id);
}
