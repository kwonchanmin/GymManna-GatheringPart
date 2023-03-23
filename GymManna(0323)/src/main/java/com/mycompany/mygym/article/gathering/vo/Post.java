package com.mycompany.mygym.article.gathering.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
   
	private Long postNum;
	private Long articleGnum;
	private String userId;  // Id라 적어놓고 닉네임을 보내줄 것
}
