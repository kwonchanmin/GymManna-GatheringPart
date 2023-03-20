package com.mycompany.mygym.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.mygym.user.vo.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	//회원가입
//	@Override
//	public int insertUser(User user) {
//		log.debug("회원가입dao");
//		
//		
//		return session.insert("UseUser.register", user);
//	}
//
//	@Override
//	public User selectUser(User user) {
//		log.debug("회원가입받아오는dao");
//		return session.selectOne("UseUser.regiseterChk", user);
//	}

	@Override
	public int createUser(User user) {
		
		log.debug("회원가입dao");
		return session.insert("UseUser.register", user);
	}
	
	//로그인
	@Override
	public User findByUsername(User user) {
		
//		User user = new User();
//		user.setUserId(userId);
//		log.debug(userId);
		log.debug("dao");

		//list = session.selectList("User.login", user); // db에서 select 실행해서 그 결과값 돌려받기
		User result = session.selectOne("UseUser.login", user);
		log.debug(result);
		return result;
//		log.debug((User)(session.selectOne("UseUser.login", user)));
		
//		return (User)(session.selectOne("UseUser.login", user));
	}

	//전체 회원 리스트
	@Override
	public List<User> getAllUser() {
		log.debug("admin dao");
		return session.selectList("UseUser.allUser");
	}

	//회원정보 수정
	@Override
	public int editUser(User user) {
		log.debug("회원정보수정 dao");
		return session.update("UserUser.editUser", user);
	}
	//정보 수정을 위해 불러오는 정보
	@Override
	public User selectUser(User user) {
		
		return session.selectOne("UserUser.selectUser");
	}

	//회원탈퇴
	@Override
	public int deleteUser() {
		// TODO Auto-generated method stub
		return session.delete("UserUser.withdraw");
	}
	




	
	

}
