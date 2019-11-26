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

import com.home.policies.controller.VehicleController;
import com.home.policies.dto.VehicleDto;
import com.home.policies.service.VehicleService;

@RestController
@RequestMapping("${base.url}" + "vehicle" )
@PreAuthorize("hasRole('ADMIN')")
public class VehicleControllerImpl implements VehicleController{
	
	private VehicleService vehicleService;
	
	@Autowired
	public VehicleControllerImpl(VehicleService vehicleService) {
		this.vehicleService=vehicleService;
	}
	
	@Override
    @GetMapping("/taker/{takerEmail}/")
	public Collection<VehicleDto> getVehiclesByTaker(@PathVariable("takerEmail") String takerEmail){
		return vehicleService.getVehiclesByTaker(takerEmail);
	}

	@Override
	@GetMapping("/withpolicy")
	public Collection<VehicleDto> getAllVehicleWithPolicy() {
		return vehicleService.getAllVehicleWithPolicy();
	}

	@Override
	@PostMapping
	public ResponseEntity<Void> createVehicle(@RequestBody VehicleDto vehicleDto) {
		VehicleDto vehicleCreatedDto = vehicleService.createVehicle(vehicleDto);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + vehicleCreatedDto.getId())
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@Override
	@GetMapping
	public Collection<VehicleDto> getAllVehicle(){
		return vehicleService.getAllVehicle();
	}

	@Override
	@GetMapping("/{idVehicle}")
	public VehicleDto getVehicle(@PathVariable Long idVehicle) {
		return vehicleService.getVehicle(idVehicle);
	}

	@Override
	@PutMapping("/{idVehicle}")
	public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Long idVehicle) {
		vehicleService.updateVehicle(vehicleDto, idVehicle);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{idVehicle}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long idVehicle) {
		vehicleService.deleteVehicle(idVehicle);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
