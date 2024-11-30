package com.sts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sts.payload.PostDto;
import com.sts.payload.PostResponse;
import com.sts.services.PostService;
import com.sts.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// get all posts api
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
//		return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
		return ResponseEntity.ok(postService.updatePost(postDto, id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id) {
		try {
			this.postService.deletePostById(id);
			return new ResponseEntity("Entity Deleted.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Error...!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
