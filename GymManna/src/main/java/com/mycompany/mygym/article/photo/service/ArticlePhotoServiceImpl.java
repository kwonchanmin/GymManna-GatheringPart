package com.mycompany.mygym.article.photo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.mycompany.mygym.article.photo.dao.ArticlePhotoDao;
import com.mycompany.mygym.article.photo.vo.ArticleImage;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

@Service
public class ArticlePhotoServiceImpl implements ArticlePhotoService {
	Logger log = LogManager.getLogger("case3");

//	@Autowired
//	private PlatformTransactionManager transactionManager;

	@Autowired
	private ArticlePhotoDao dao;

	@Override
	public int createPost(ArticlePhoto articlePhoto, String imageUrl) {
		int result = dao.createPost(articlePhoto);
		long articleNum = 0;
		ArticleImage articleImage = null;
		
		if(result != 0) {
			articleNum = dao.getNowCreatedId();
			articleImage.setArticlePnum(articleNum);
			articleImage.setArticleImgurl(imageUrl);
			
			dao.createImage(articleImage);
		}else {
			return 0;
		}
		return 0;
	}

	@Override
	public List<ArticlePhoto> getArticle() {
		return dao.getArticle();
	}
}
