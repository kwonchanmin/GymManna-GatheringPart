package com.mycompany.mygym.article.photo.service;

import java.util.List;


import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

public interface ArticlePhotoService {
	int createPost(ArticlePhoto articlePhoto, String imageUrl);
	
	List<ArticlePhoto> getArticle();
}
