package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.payload.CommentDto;
import com.sts.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsById(@PathVariable(value = "postId") long postId) {
		return commentService.getAllCommentsByPostId(postId);
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(
			@PathVariable(value = "postId") long postId, 
			@PathVariable(value = "commentId") long commentId){
		
			CommentDto commentDto = commentService.getCommentById(postId, commentId);
		
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}

}
