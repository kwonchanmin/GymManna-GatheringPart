package com.mycompany.mygym.article.photo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.mygym.article.photo.vo.ArticleImage;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

@Repository
public class ArticlePhotoDaoImpl implements ArticlePhotoDao{
	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int createPost(ArticlePhoto articlePhoto) {
		log.debug("2. [DAO] 게시글 생성하기");
		return session.insert("com.mycompany.mygym.article.photo.createPost", articlePhoto);
	}

	@Override
	public List<ArticlePhoto> getArticle() {
		return session.selectList("com.mycompany.mygym.article.photo.selectArticle");
	}

	@Override
	public long getNowCreatedArticle() {
		log.debug("4. [DAO] 게시글 ID 가져오기");
		return session.selectOne("com.mycompany.mygym.article.photo.selectNowCreatedArticle");
	}

	@Override
	public int createImage(ArticleImage articleImage) {
		log.debug("5. [DAO] 이미지 등록하기");
		return session.insert("com.mycompany.mygym.article.photo.createImage", articleImage);
		
	}
}
