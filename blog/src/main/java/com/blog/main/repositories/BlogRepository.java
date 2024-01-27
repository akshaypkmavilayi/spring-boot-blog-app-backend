package com.blog.main.repositories;

import com.blog.main.entities.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogPostEntity,Long> {
}
