package com.home.policies;

import java.util.ArrayList;
import java.util.List;

import com.home.policies.dto.PolicyDto;
import com.home.policies.dto.TakerDto;
import com.home.policies.dto.VehicleDto;
import com.home.policies.model.entities.Policy;
import com.home.policies.model.entities.Taker;
import com.home.policies.model.entities.Vehicle;

public class Utils {
	
	public static PolicyDto getNewpolicyDtoDummie() {
		PolicyDto policyDto = new PolicyDto();
    	policyDto.setId(1L);
    	policyDto.setProduct("productoDummie");
    	
    	TakerDto takerDto = new TakerDto();
    	takerDto.setEmail("email");
    	takerDto.setId(1L);
    	policyDto.setTaker(takerDto);
    	
    	VehicleDto vehicleDto = new VehicleDto();
    	vehicleDto.setId(1L);
    	vehicleDto.setLicensePlate("licenseplate random");
    	policyDto.setVehicle(vehicleDto);
    	
    	return policyDto;
	}
	
	public static Policy getNewPolicyDummie() {
		Policy policyEntity = new Policy();
    	policyEntity.setId(1L);
    	policyEntity.setProduct("productoDummie");
    	
    	Vehicle vehicleEntity = new Vehicle();
    	vehicleEntity.setId(1L);
    	vehicleEntity.setLicensePlate("licenseplate random");
    	policyEntity.setVehicle(vehicleEntity);
    	
    	Taker takerEntity = new Taker();
    	takerEntity.setEmail("email");
    	takerEntity.setId(1L);
    	policyEntity.setTaker(takerEntity);
    	
    	return policyEntity;
	}
	
	public static List<Policy> getTenPolicyDummies() {
		Integer count = 10;
        ArrayList<Policy> results = new ArrayList<>();
        Policy policy;
        Taker taker;
        Vehicle vehicle;
        for(int i = 0; i<count; i++){
        	
        	taker = new Taker();
        	taker.setEmail("email"+i);
        	taker.setId(1L+i);
        	vehicle = new Vehicle();
        	vehicle.setId(1L+i);
        	vehicle.setLicensePlate("licenseplate random");
        	
        	policy = new Policy();
        	policy.setId(1L+i);
        	policy.setTaker(taker);
        	policy.setVehicle(vehicle);
        	policy.setProduct("productoDummie");
            results.add(policy);
        }
        return results;
	}
}
