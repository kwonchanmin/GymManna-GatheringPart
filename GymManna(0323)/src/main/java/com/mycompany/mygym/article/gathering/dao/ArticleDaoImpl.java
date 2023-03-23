package com.mycompany.mygym.article.gathering.dao;

import java.util.List;

import com.mycompany.mygym.article.gathering.vo.Gathering;
import com.mycompany.mygym.article.gathering.vo.Post;

public interface ArticleDaoImpl {

	List<Gathering> selectText(Gathering gathering);
	
	List<Gathering> selectGnum(Gathering gathering);
	
	List<Gathering> selectGatheringList(Gathering gathering);
	
	Gathering selectInfo(Gathering gathering);
	
	int createArticle(Gathering gathering);
	
	int updateArticle(Gathering gathering);
	
	int deleteArticle(Gathering gathering);
	
	List<Gathering> selectMyManna(Gathering gathering);
	
	Post selectUser(Post post);
	
	int deleteUser(Post post);
	
	int insertUser(Post post);
}
