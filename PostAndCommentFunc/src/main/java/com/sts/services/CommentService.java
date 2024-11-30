package com.sts.services;

import java.util.List;

import com.sts.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(long postId, CommentDto commentDto); 
	
	List<CommentDto> getAllCommentsByPostId(long postId);
	
	CommentDto getCommentById(long postId, long commentId);

}
