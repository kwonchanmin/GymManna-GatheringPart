package com.mycompany.mygym.user;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.mygym.user.service.UserService;
import com.mycompany.mygym.user.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequestMapping("user")
@SessionAttributes("newUser")
public class UserController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private UserService userService;
	
	//회원가입 통로
	@GetMapping(value = "/registerjsp")
	public String registerJsp() {
		
		return "user/userRegister";
	}
	
//	@RequestMapping(value="newUser")
//	public User newUser() {
//		User user = new User();
//		return user;
//	}
	
	//회원가입
	@PostMapping(value = "/register")
	public String registerHandler(@ModelAttribute User user, Model model) {
		
		
//		boolean use = 
				userService.createUser(user);
		log.debug("회원가입컨트롤러");
//		model.addAttribute("message", "회원가입이 완료되었습니다.");
		
		return "user/userLogin";
	}
		
	//로그인
	@GetMapping(value = "/login")
	public String loginHandler(Model model, String userId, String userPw) {
		log.debug("컨트롤러");
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPw);
		log.debug(user);
		User result = userService.loginUser(user);
		
		model.addAttribute("user", user);
		
		if(result == null ) {
			log.debug("서비스 실패");
			return "user/loginFail";
		} else {
			return "user/loginSuccess";
		}
	}
	
	//회원정보수정
	@PostMapping(value = "/update")
	public String updateHandler(User user, Model model) {
		log.debug("update컨트롤러");
		
		userService.editUser(user);
		return null;
	}
	
	//전체회원 리스트 불러오기
	@GetMapping(value = "/admin")
	public String adminHandler(Model model){
		log.debug("admin컨트롤러");
		List<User> user = userService.allUser();
		 model.addAttribute("user", user);

		return "user/allUser";
	}
	
	//회원 탈퇴
	@DeleteMapping(value = "/")
	public void deleteHandler() {
		userService.deleteUser();
	}

}
