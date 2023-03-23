package com.mycompany.mygym.comment.gathering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 만나 댓글 생성
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
	
	// 만나 댓글 수정
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
	
	// 만나 댓글 삭제
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
	
	// 마이페이지 만나 댓글 조회
	@GetMapping(value="myComment")
	public ResponseEntity<List<GComment>> myComment(@RequestParam String userNickname, Model model) {
		
		GComment comment = new GComment();
		comment.setUserNickname(userNickname);
		
		List<GComment> list = service.selectMyComment(comment);
		
		return ResponseEntity.ok(list);
		
	}
}
