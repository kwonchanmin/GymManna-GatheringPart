package com.mycompany.mygym.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String userId;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userNickname;
	private int userIsadmin;
	private int userRoute;
	
}
