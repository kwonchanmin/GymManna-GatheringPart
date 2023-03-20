package com.mycompany.mygym.comment.gathering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.mygym.comment.gathering.service.CommentServiceImpl;
import com.mycompany.mygym.comment.gathering.vo.GComment;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentServiceImpl service;
	
	@PostMapping(value="create", produces="application/json; charset=UTF-8")
	public ResponseEntity<GComment> insertComment(@RequestParam String commentGcontent,@RequestParam Long articleGnum,@RequestParam String userNickname ,Model model ) {
		
		GComment comment = new GComment();
		comment.setArticleGnum(articleGnum);
		comment.setCommentGcontent(commentGcontent);
		comment.setUserNickname(userNickname);
		
		boolean result = service.insertComment(comment);
		
		if(result != false) {
			List<GComment> list = service.selectList(comment);
			model.addAttribute("CList",list);
		}
		
		return ResponseEntity.ok(comment);
	}
	
	@PutMapping(value="update", produces="application/json; charset=UTF-8")
	public ResponseEntity<GComment> updateComment(@RequestParam String commentGcontent, @RequestParam Long commentGnum, Model model) {
		
		GComment comment = new GComment();
		comment.setCommentGnum(commentGnum);
		comment.setCommentGcontent(commentGcontent);
		
		boolean result = service.updateComment(comment);
		
		if(result != false) {
			List<GComment> list = service.selectList(comment);
			model.addAttribute("CList",list);
		}
		
		return ResponseEntity.ok(comment);
		
	}
	
	@DeleteMapping(value="delete", produces="application/json; charset=UTF-8")
	public ResponseEntity<GComment> deleteComment(@RequestParam Long commentGnum, Model model) {
		
		GComment comment = new GComment();
		comment.setCommentGnum(commentGnum);
		
		boolean result = service.deleteComment(comment);
		
		if(result != false) {
			List<GComment> list = service.selectList(comment);
			model.addAttribute("CList",list);
		}
		
		return ResponseEntity.ok(comment);
	}
	
}
