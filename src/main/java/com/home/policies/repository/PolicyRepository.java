package com.home.policies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.policies.model.entities.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long>{
	@Modifying
	@Query("delete from Policy pol where pol.id=:id")
	public void deleteById(@Param("id") Long id);
	
	//It is not necessary, only for learning purposes
	//By default taker is EAGER
	@EntityGraph(attributePaths = {"taker"}, type = EntityGraphType.LOAD)
	public Optional<Policy> findById(Long idPolicy);
	
	@EntityGraph(attributePaths = {"vehicle", "taker"}, type = EntityGraphType.FETCH)
	public List<Policy> findAll();
}
