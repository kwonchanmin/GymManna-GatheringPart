package com.mycompany.mygym.article.gathering.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gathering {

	private Long articleGnum;
	private String articleGtitle;
	private String articleGcontent;
	private Date articleGdate;
	private String userId;
	private String categoryType;
	private Long centerNum;
	private String centerName;
	private int countGnum;
	private int countPuser;
	private String joinUser;
}