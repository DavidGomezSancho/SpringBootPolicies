package com.home.policies.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.home.policies.dto.PolicyDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Policy"})
public interface PolicyController {
	
	@ApiOperation(value = "Get Policy", 
			notes = "Return is the Policy by id provided in the URL")
	public PolicyDto getPolicy(Long id);
	
	@ApiOperation(value = "Create Policy", 
			notes = "Create policy by info provided in the body")
	public ResponseEntity<Void> createPolicy(PolicyDto policyDto);
	
	@ApiOperation(value = "Get All Policies", 
			notes = "Return are all the policies in the database")
	public Collection<PolicyDto> getAllPolicy();
	
	@ApiOperation(value = "Update a policy", 
			notes = "Update a policy from a PolicyDto")
	public ResponseEntity<Void> updatePolicy(PolicyDto policyDto, Long idPolicy);
	
	@ApiOperation(value = "Delete a policy", 
			notes = "Delete a policy from an idPolicy")
	public ResponseEntity<Void> deletePolicy(Long idPolicy);
}
