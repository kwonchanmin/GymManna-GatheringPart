package com.mycompany.mygym.user.dao;

import java.util.List;

import com.mycompany.mygym.user.vo.User;


public interface UserDao {


//	User selUser(User user);
	
	//회원가입
//	int insertUser(User user);
//	User selectUser(User user);
	int createUser(User user);
	
	//로그인
	User findByUsername(User user);

	//전체회원리스트 불러오기
	List<User> getAllUser();
	
	//회원정보수정
	User selectUser(User user); //정보 볼러오기
	int editUser(User user); //정보수정
	
	//회원 탈퇴
	int deleteUser();
}
