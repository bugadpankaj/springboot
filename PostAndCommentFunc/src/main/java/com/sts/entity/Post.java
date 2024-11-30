package com.sts.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Posts", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Title", nullable = false)
	private String title;

	@Column(name = "Description", nullable = false)
	private String description;

	@Column(name = "Content", nullable = false)
	private String content;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();

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

	public Post(Long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

}
