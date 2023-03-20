package com.mycompany.mygym.article.photo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.mygym.article.photo.vo.ArticleImage;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

@Repository
public class ArticlePhotoDaoImpl implements ArticlePhotoDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int createPost(ArticlePhoto articlePhoto) {
		return session.insert("com.mycompany.mygym.article.photo.createPost", articlePhoto);
	}

	@Override
	public List<ArticlePhoto> getArticle() {
		return session.selectList("com.mycompany.mygym.article.photo.selectArticle");
	}

	@Override
	public long getNowCreatedId() {
		return session.selectOne("com.mycompany.mygym.article.photo.selectNowCreatedArticle");
	}

	@Override
	public int createImage(ArticleImage articleImage) {
		return session.insert("com.mycompany.mygym.article.image.createImage", articleImage);
		
	}
}
