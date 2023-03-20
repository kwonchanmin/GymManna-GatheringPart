package com.mycompany.mygym.article.photo.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePhotoImage {
	private long articlePnum;
	private String articlePtitle;
	private String articlePcontent;
	private Date articlePdate;
	private String userId;
	private String imageUrl;
}
