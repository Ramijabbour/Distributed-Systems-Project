package com.example.Rate.Rates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RateRepository extends JpaRepository<RateModel, Integer> {
	
	public List<RateModel> findByarticleId(int articleId);


}
