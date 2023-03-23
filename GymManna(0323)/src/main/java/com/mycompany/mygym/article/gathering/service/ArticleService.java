package com.mycompany.mygym.article.gathering.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.mygym.article.gathering.dao.ArticleDao;
import com.mycompany.mygym.article.gathering.vo.Center;
import com.mycompany.mygym.article.gathering.vo.Gathering;
import com.mycompany.mygym.article.gathering.vo.Post;

@Service
public class ArticleService implements ArticleServiceImpl {

	@Autowired
	private ArticleDao dao;
	
	Logger log = LogManager.getLogger("case3");

	// 만나 센터 별 게시글 수 확인
	@Override
	public List<Gathering> readText(Gathering gathering) {
		
		List<Gathering> count = dao.selectText(gathering);
		
		return count;
	}
	// 만나 센터별 게시글 번호
	@Override
	public List<Gathering> selectGnum(Gathering gathering) {
		
		List<Gathering> result = dao.selectGnum(gathering);
		
		return result;
	}
	
	// 만나 센터의 게시글 리스트
	@Override
	public List<Gathering> selectGatheringList(Gathering gathering) {
		
		List<Gathering> list = dao.selectGatheringList(gathering);
		
		log.debug("서비스"+gathering);
		log.debug("서비스"+list);
		
		return list;
	}

	// 만나 게시글의 상세보기
	@Override
	public Gathering selectInfo(Gathering gathering) {
		
		Gathering info = dao.selectInfo(gathering);
		
		return info;
	}

	// 만나 게시글 작성
	@Override
	public List<Gathering> createArticle(Gathering gathering) {
		
		int count = dao.createArticle(gathering);
		
		List<Gathering> list = dao.selectGatheringList(gathering);
		
		return list;
	}

	@Override
	public Gathering updateArticle(Gathering gathering) {
		
		int count = dao.updateArticle(gathering);
		
		Gathering result = dao.selectInfo(gathering);
		return result;
	}

	@Override
	public List<Gathering> deleteArticle(Gathering gathering) {
	
		int count = dao.deleteArticle(gathering);
		
		List<Gathering> list = dao.selectGatheringList(gathering);
		
		return list;
	}
	@Override
	public List<Gathering> selectMyManna(Gathering gathering) {
		
		List<Gathering> list = dao.selectMyManna(gathering);
		
		return list;
	}
	@Override
	public Post selectUser(Post post) {
		
		Post result = dao.selectUser(post);
		
		return result;
	}
	@Override
	public boolean deleteUser(Post post) {
		
		int count = dao.deleteUser(post);
		if(count == 1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean insertUser(Post post) {
	
		int count = dao.insertUser(post);
		if(count == 1) {
			return true;
		}else {
			return false;
		}
	}



	
}
