package com.sts.payload;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sts.entity.Comment;

public class PostDto {
	
	private Long id;
	private String title;
	private String description;
	private String content;
	
	private Set<Comment> comments;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public PostDto(Long id, String title, String description, String content, Set<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
