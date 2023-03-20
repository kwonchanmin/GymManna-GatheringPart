package com.mycompany.mygym.comment.gathering.service;

import java.util.List;

import com.mycompany.mygym.comment.gathering.vo.GComment;

public interface CommentService {

	List<GComment> selectList(GComment comment);
	
	boolean insertComment(GComment comment); 
	
	boolean updateComment(GComment comment); 
	
	boolean deleteComment(GComment comment); 
}
