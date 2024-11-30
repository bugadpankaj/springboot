package com.sts.payload;

import com.sts.entity.Post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {

	private long id;
	@NotEmpty(message = "Name shold not be empty")
	@NotNull(message = "Name shold not be null")
	private String name;
	@NotEmpty(message = "Email shold not be empty")
	@NotNull(message = "Email shold not be null")
	private String email;
	@NotEmpty(message = "Body shold not be empty")
	@NotNull(message = "Body shold not be null")
	@Size(min = 5, message = "Atleast 5 characters")
	private String body;

	private Post post;

	public CommentDto(long id, String name, String email, String body, Post post) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
