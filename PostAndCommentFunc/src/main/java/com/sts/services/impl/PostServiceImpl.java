package com.sts.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sts.dao.PostRepositoty;
import com.sts.entity.Post;
import com.sts.exception.ResourceNotFoundException;
import com.sts.payload.PostDto;
import com.sts.payload.PostResponse;
import com.sts.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepositoty postRepositoty;
	private ModelMapper mapper;

	@Autowired
	public PostServiceImpl(PostRepositoty postRepositoty, ModelMapper mapper) {
		this.postRepositoty = postRepositoty;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
//		convert dto into object to save into database

		Post post = mapToPost(postDto);

		// Save in to database

		Post newPost = postRepositoty.save(post);

		// Convert new post into DTO

		PostDto responsePostDto = mapToDto(newPost);

		return responsePostDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//		For decending order
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		
		Page<Post> posts = postRepositoty.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> content = listOfPosts.stream().map(post -> (mapToDto(post))).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}

	private PostDto mapToDto(Post newPost) {
		PostDto responsePostDto = mapper.map(newPost, PostDto.class);
				
//				new PostDto();
//		responsePostDto.setId(newPost.getId());
//		responsePostDto.setTitle(newPost.getTitle());
//		responsePostDto.setDescription(newPost.getDescription());
//		responsePostDto.setContent(newPost.getContent());
//		responsePostDto.setComments(newPost.getComments());
//		
		return responsePostDto;
	}

	private Post mapToPost(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
				
//				new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
//		post.setComments(postDto.getComments());

		return post;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepositoty.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post = postRepositoty.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepositoty.save(post); 
		
		return mapToDto(updatedPost);
	}

	@Override
	public void deletePostById(long id) {
		
		Post post = postRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepositoty.delete(post);
	}
}
