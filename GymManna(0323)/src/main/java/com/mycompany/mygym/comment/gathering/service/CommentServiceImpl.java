package com.mycompany.mygym.comment.gathering.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mycompany.mygym.comment.gathering.dao.CommentDaoImpl;
import com.mycompany.mygym.comment.gathering.vo.GComment;

@Service
public class CommentServiceImpl implements CommentService {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	@Qualifier("commentDao")
	private CommentDaoImpl dao;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	public List<GComment> selectList(GComment comment) {
		
		List<GComment> list = null;
		
		list = dao.selectList(comment);
		
		return list;
	}

	@Override
	public boolean insertComment(GComment comment) {
		
		// Transaction 시작
		TransactionStatus txStatus =transactionManager.getTransaction(new DefaultTransactionDefinition());
				
		// TransactionStatus txStatus = null;
		
		boolean count = false;
		
		try {
			dao.insertComment(comment);
			
			count = true;
			transactionManager.commit(txStatus);
			
			// Transaction을 SqlSession을 사용한 후에 시작하면 당연히 처리가 안됨.			
			// txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		} catch (Exception e) {
			count = false;
			transactionManager.rollback(txStatus);
		}
		
		return count;
	}
	
	

	@Override
	public boolean updateComment(GComment comment) {
		
		// Transaction 시작
		TransactionStatus txStatus =transactionManager.getTransaction(new DefaultTransactionDefinition());
				
		// TransactionStatus txStatus = null;
		
		boolean count = false;
		
		try {
			dao.updateComment(comment);
			
			count = true;
			transactionManager.commit(txStatus);
			
			// Transaction을 SqlSession을 사용한 후에 시작하면 당연히 처리가 안됨.			
			// txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		} catch (Exception e) {
			count = false;
			transactionManager.rollback(txStatus);
		}
		
		return count;
	}

	@Override
	public boolean deleteComment(GComment comment) {
		// Transaction 시작
		TransactionStatus txStatus =transactionManager.getTransaction(new DefaultTransactionDefinition());
				
		// TransactionStatus txStatus = null;
		
		boolean count = false;
		
		try {
			dao.deleteComment(comment);
			
			count = true;
			transactionManager.commit(txStatus);
			
			// Transaction을 SqlSession을 사용한 후에 시작하면 당연히 처리가 안됨.			
			// txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		} catch (Exception e) {
			count = false;
			transactionManager.rollback(txStatus);
		}
		
		return count;
	}

	@Override
	public List<GComment> selectMyComment(GComment comment) {
		
		List<GComment> list = dao.selectMyComment(comment);
		
		return list;
	}
	
}
