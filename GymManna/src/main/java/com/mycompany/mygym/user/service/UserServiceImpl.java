package com.mycompany.mygym.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mycompany.mygym.user.dao.UserDao;
import com.mycompany.mygym.user.vo.User;

@Service
public class UserServiceImpl implements UserService {

	Logger log = LogManager.getLogger("case3");
	
//	@Autowired
//	private PlatformTransactionManager transactionManager;
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;


	@Override
	public boolean createUser(User user) {
		log.debug("회원가입서비스");
//		TransactionStatus txStatus = 
//				transactionManager.getTransaction(new DefaultTransactionDefinition());
		
//		boolean result = false;
		
//		try {
			userDao.createUser(user);
			
//			result = true;
//			transactionManager.commit(txStatus);	
//			
//		} catch (Exception e) {
//			result = false;
//			transactionManager.rollback(txStatus);
//		}
		return true;
//		return result;
	}
	
	//로그인
	@Override
    public User loginUser(User user) {
		
		
		log.debug("서비스");
		log.debug(user);
//		log.debug(user.getUserId());
//		log.debug(user.getUserPassword());

		//dao일시키기
		User result = userDao.findByUsername(user);
		log.debug(result);
		//if문 아이디 비번 틀렷을때
		if(result != null && result.getUserPassword().equals(user.getUserPassword())) {
			log.debug("로그인 성공");
			return result;
		} else {
			log.debug("아이디비번 틀림");
			return null;
		}
	}

	//전체회원리스트
	@Override
	public List<User> allUser() {
		log.debug("admin서비스");
		return userDao.getAllUser();
	}

	//회원정보 수정
	@Override
	public boolean editUser(User user) {
		log.debug("회원정보 수정 서비스");
		userDao.selectUser(user);
		userDao.editUser(user);
		return true;
	}

	//회원탈퇴
	@Override
	public int deleteUser() {
		
		return userDao.deleteUser();
	}


	



	
	
}
	
	

	    


	


