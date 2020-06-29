package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.RatingModel;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Integer> {
	
	public List<RatingModel> findByarticleId(int articleId);

}
