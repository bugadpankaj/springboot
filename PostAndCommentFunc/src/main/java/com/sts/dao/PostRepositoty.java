package com.sts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.entity.Post;

public interface PostRepositoty extends JpaRepository<Post, Long>{
}
