package com.home.policies.controller.Impl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.home.policies.controller.PolicyController;
import com.home.policies.dto.PolicyDto;
import com.home.policies.service.PolicyService;

@RestController
@RequestMapping("${base.url}" + "policy" )
@PreAuthorize("hasRole('ADMIN') and #oauth2.hasScope('read')")
public class PolicyControllerImpl implements PolicyController{
	
	private PolicyService policyService;
	
	@Autowired
	public PolicyControllerImpl(PolicyService policyService) {
		this.policyService=policyService;
	}
	
	@Override
    @GetMapping("/{policyId}")
	@PreAuthorize("(hasRole('ADMIN') OR hasRole('USER')) and #oauth2.hasScope('read')")
	public PolicyDto getPolicy(@PathVariable Long policyId){
		return policyService.getPolicy(policyId);
	}
	
	@Override
    @PostMapping
	public ResponseEntity<Void> createPolicy(@RequestBody PolicyDto policyDto){
		PolicyDto policyCreatedDto = policyService.createPolicy(policyDto);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + policyCreatedDto.getId())
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@Override
	@GetMapping
	@PreAuthorize("(hasRole('ADMIN') OR hasRole('USER')) and #oauth2.hasScope('read')")
	public Collection<PolicyDto> getAllPolicy() {
		return policyService.getAllPolicy();
	}

	@Override
	@PutMapping("/{idPolicy}")
	public ResponseEntity<Void> updatePolicy(@RequestBody PolicyDto policyDto, 
			@PathVariable Long idPolicy) {
		policyService.updatePolicy(policyDto, idPolicy);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("{idPolicy}")
	public ResponseEntity<Void> deletePolicy(@PathVariable Long idPolicy) {
		policyService.deletePolicy(idPolicy);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
