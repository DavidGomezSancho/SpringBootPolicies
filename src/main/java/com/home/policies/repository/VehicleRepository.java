package com.home.policies.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.policies.model.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	public Optional<Vehicle> findByLicensePlate(String licensePlate);
	
	public Collection<Vehicle> findVehiclesByTakerEmail(@Param("emailTaker") String emailTaker);
	
	@Modifying
	@Query("delete from Vehicle vehicle where vehicle.id=:id")
	public void deleteById(@Param("id") Long id);
	
	public Boolean existsByLicensePlate(String licensePlate);
}
