package com.mycompany.mygym.article.photo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mycompany.mygym.article.photo.dao.ArticlePhotoDao;
import com.mycompany.mygym.article.photo.vo.ArticleImage;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

@Service
public class ArticlePhotoServiceImpl implements ArticlePhotoService {
	Logger log = LogManager.getLogger("case3");

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private ArticlePhotoDao dao;

	@Override
	public int createPost(ArticlePhoto articlePhoto, String imageUrl) {
		// Log4J
		Logger log = LogManager.getLogger("case3");
		
		// 트랜잭션
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		// 결과
		int result = 0;
		
		// 게시글 등록
		int resultCreatePost = dao.createPost(articlePhoto);
		
		// 게시글 PK
		long articleNum = 0;
		
		// 이미지 VO
		ArticleImage articleImage = new ArticleImage();
		
		log.debug("1. [Service] 접속");
		
		try {
			// 게시글 등록 성공 시
			if (resultCreatePost != 0) {
				log.debug("3. [Service] 게시글 등록 성공");
				
				articleNum = dao.getNowCreatedArticle();
				articleImage.setArticlePnum(articleNum);
				articleImage.setArticleImgurl(imageUrl);
				
				// 이미지 등록
				dao.createImage(articleImage);
				log.debug("7. [Service] 이미지 등록 성공");

				transactionManager.commit(status);
				
				result = 1;
			}
		} catch (Exception e) {
			transactionManager.rollback(status);
		}

		return result;
	}

	@Override
	public List<ArticlePhoto> getArticle() {
		return dao.getArticle();
	}
}
