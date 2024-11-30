package com.sts.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sts.dao.CommentRepository;
import com.sts.dao.PostRepositoty;
import com.sts.entity.Comment;
import com.sts.entity.Post;
import com.sts.exception.BlogApiException;
import com.sts.exception.ResourceNotFoundException;
import com.sts.payload.CommentDto;
import com.sts.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepositoty postRepositoty;
	private ModelMapper mapper;
	
	

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, PostRepositoty postRepositoty, ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepositoty = postRepositoty;
		this.mapper = mapper;
	}

	private CommentDto mapToDto(Comment comment) {

		CommentDto commentDto = mapper.map(comment, CommentDto.class);
				
//				new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {

		Comment comment = mapper.map(commentDto, Comment.class);
				
//				new Comment();
//		comment.setId(commentDto.getId());
//		comment.setBody(commentDto.getBody());
//		comment.setEmail(commentDto.getEmail());
//		comment.setName(commentDto.getName());

		return comment;

	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {

		Comment comment = mapToEntity(commentDto);

		// retrive post entity by id

		Post post = postRepositoty.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// set post to comment
		comment.setPost(post);

		// save comment in database
		Comment savedComment = commentRepository.save(comment);

		return mapToDto(savedComment);
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(long postId) {

		List<Comment> comments = commentRepository.findByPostId(postId);

		// converting comments entity list into commentDto and sending it
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {

		Post post = postRepositoty.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
		}
		
		return mapToDto(comment);
	}

}
