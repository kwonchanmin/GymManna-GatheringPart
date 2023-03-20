package com.mycompany.mygym.article.gathering.service;

import java.util.List;

import com.mycompany.mygym.article.gathering.vo.Gathering;

public interface ArticleServiceImpl {

	List<Gathering> readText(Gathering gathering);
	
	List<Gathering> selectGnum(Gathering gathering);
	
	List<Gathering> selectGatheringList(Gathering gathering);
	
	Gathering selectInfo(Gathering gathering);
	
	List<Gathering> createArticle(Gathering gathering);
	
	Gathering updateArticle(Gathering gathering);
	
	List<Gathering> deleteArticle(Gathering gathering);
}
