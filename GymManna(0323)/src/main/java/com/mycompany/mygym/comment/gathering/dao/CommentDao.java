package com.mycompany.mygym.comment.gathering.dao;

import java.util.List;

import com.mycompany.mygym.comment.gathering.vo.GComment;

public interface CommentDao {

	List<GComment> selectList(GComment comment);
	
	int insertComment(GComment comment);
	
	int updateComment(GComment comment);
	
	int deleteComment(GComment comment);
	
	List<GComment> selectMyComment(GComment comment);
}
