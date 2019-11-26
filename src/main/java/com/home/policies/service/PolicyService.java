package com.home.policies.service;

import java.util.Collection;

import com.home.policies.dto.PolicyDto;

public interface PolicyService {
	public PolicyDto getPolicy(Long policyId);
	public PolicyDto createPolicy(PolicyDto policyDto);
	public Collection<PolicyDto> getAllPolicy();
	public PolicyDto updatePolicy(PolicyDto policyDto, Long idPolicy);
	public void deletePolicy(Long idPolicy);
}
