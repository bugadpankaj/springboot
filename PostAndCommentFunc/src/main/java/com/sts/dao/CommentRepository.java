package com.sts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostId(long postId);

}
