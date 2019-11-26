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

import com.home.policies.controller.TakerController;
import com.home.policies.dto.TakerDto;
import com.home.policies.service.TakerService;

@RestController
@RequestMapping("${base.url}" + "taker" )
@PreAuthorize("hasRole('ADMIN')")
public class TakerControllerImpl implements TakerController{

	private TakerService takerService;
	
	@Autowired
	public TakerControllerImpl(TakerService takerService) {
		this.takerService=takerService;
	}
	
	@Override
	@PutMapping("/{idTaker}")
	public ResponseEntity<Void> updateTaker(@RequestBody TakerDto takerDto, @PathVariable Long idTaker) {
		takerService.updateTaker(takerDto, idTaker);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{idTaker}")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public TakerDto getTaker(@PathVariable Long idTaker) {
		return takerService.getTaker(idTaker);
	}

	@Override
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public Collection<TakerDto> getAllTaker() {
		return takerService.getAllTaker();
	}

	@Override
	@PostMapping
	public ResponseEntity<Void> createTaker(@RequestBody TakerDto takerDto) {
		TakerDto takerCreateDto = takerService.createTaker(takerDto);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + takerCreateDto.getId())
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping("{idTaker}")
	public ResponseEntity<Void> deleteTaker(@PathVariable Long idTaker) {
		takerService.deleteTaker(idTaker);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
