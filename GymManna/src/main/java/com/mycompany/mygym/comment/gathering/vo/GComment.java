package com.mycompany.mygym.comment.gathering.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GComment {

	private Long commentGnum;
	private Date commentGdate;
	private Long articleGnum;
	private String userNickname;
	private String commentGcontent;
}
