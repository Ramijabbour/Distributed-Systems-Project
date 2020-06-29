package com.example.comment.Comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel,Integer> {

	public List<CommentModel> findByarticleId(int articleId);
	
}
