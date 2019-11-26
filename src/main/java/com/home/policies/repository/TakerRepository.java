package com.home.policies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.policies.model.entities.Taker;

public interface TakerRepository extends JpaRepository<Taker, Long>{
	
	public Optional<Taker> findByEmail(String email);
	
	@Query(value="SELECT new java.lang.Boolean(count(*) > 0) FROM Policy pol WHERE pol.taker.id = :idTaker")
	public Boolean findAllPolicyByTakerTrue(@Param("idTaker") Long idTaker);
	
	@Modifying
	@Query("delete from Taker taker where taker.id=:id")
	public void deleteById(@Param("id") Long id);
	
	public Boolean existsByEmail(String email);
}
