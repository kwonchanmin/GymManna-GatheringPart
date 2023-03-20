package com.mycompany.mygym.article.photo;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.mygym.article.photo.service.ArticlePhotoService;
import com.mycompany.mygym.article.photo.service.StorageService;
import com.mycompany.mygym.article.photo.vo.ArticlePhoto;

@RestController
@RequestMapping(value = "/article/*")
@CrossOrigin(origins = "http://localhost:8081")
public class ArticlePhotoController {
	Logger log = LogManager.getLogger("case3");

	@Autowired
	private ArticlePhotoService service;
	
	@Autowired
	private StorageService storage;

	// [Read]
	@GetMapping(value = "/photo")
	public ResponseEntity<List<ArticlePhoto>> getPhotoList() {
		List<ArticlePhoto> list = service.getArticle();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// [Create]
	@PostMapping(value = "/photo")
	public ResponseEntity<?> createPost(@ModelAttribute ArticlePhoto articlePhoto,
			@RequestPart("image") MultipartFile image) throws IOException {
		
		String imageUrl = storage.store(image);
		int result = service.createPost(articlePhoto, imageUrl);
		
		if(result == 1) {
			return new ResponseEntity<>("게시글 등룍이 완료됐습니다.", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("게시글 등룍에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
