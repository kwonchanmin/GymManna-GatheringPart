package com.mycompany.mygym.article.photo.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.mygym.article.photo.vo.ArticleImage;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

public interface ArticlePhotoDao {	
	int createPost(ArticlePhoto articlePhoto);

	List<ArticlePhoto> getArticle();

	long getNowCreatedId();

	int createImage(ArticleImage articleImage);

}
