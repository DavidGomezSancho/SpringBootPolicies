package com.home.policies.service.Impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.home.policies.core.exception.ElementAlreadyExistsInDB;
import com.home.policies.core.exception.ElementNotFoundDBException;
import com.home.policies.dto.VehicleDto;
import com.home.policies.dto.mapper.VehicleMapper;
import com.home.policies.dto.validator.VehicleDtoValidator;
import com.home.policies.model.entities.Vehicle;
import com.home.policies.repository.VehicleRepository;
import com.home.policies.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	private VehicleRepository vehicleRepository;
	private VehicleMapper vehicleMapper;
	private MessageSource messageSource;
	private VehicleDtoValidator vehicleDtoValidator;
	
	@Autowired
	public VehicleServiceImpl(VehicleRepository vehicleRepository, 
			VehicleMapper vehicleMapper, MessageSource messageSource,
			VehicleDtoValidator vehicleDtoValidator) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleMapper = vehicleMapper;
		this.messageSource = messageSource;
		this.vehicleDtoValidator = vehicleDtoValidator;
	}
	
	@Override
	public Vehicle findOrCreateVehicle(VehicleDto vehicleDto) {
		return vehicleRepository.findByLicensePlate(vehicleDto.getLicensePlate())
				.orElseGet(() -> vehicleRepository.save(
						vehicleMapper.toEntityFromDtoWithoutId(vehicleDto)));
	}
	
	@Override
	public Collection<VehicleDto> getVehiclesByTaker(String takerEmail){
		return vehicleRepository.findVehiclesByTakerEmail(takerEmail).stream()
				.map((vehicle)->vehicleMapper.toDtoFromEntity(vehicle))
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<VehicleDto> getAllVehicleWithPolicy(){
		return vehicleRepository.findAll().stream()
			.filter((vehicle) -> vehicle.getPolicy().isPresent())
			.map((vehicle) -> vehicleMapper.toDtoFromEntity(vehicle))
			.sorted(Comparator.comparing(VehicleDto::getLicensePlate))
			.collect(Collectors.toList());
	}

	@Override
	public VehicleDto updateVehicle(VehicleDto vehicleDto, Long idVehicle) {
		vehicleDtoValidator.validatePut(vehicleDto, idVehicle);
		vehicleRepository.findById(idVehicle)
			.orElseThrow(()->new ElementNotFoundDBException(messageSource));
		return vehicleMapper.toDtoFromEntity(
				vehicleRepository.save(
						vehicleMapper.toEntityFromDtoWithId(vehicleDto)));
	}
	
	@Override
	public Vehicle getOneVehicle(Long idVehicle) {
		return vehicleRepository.getOne(idVehicle);
	}

	@Override
	public VehicleDto createVehicle(VehicleDto vehicleDto) {
		if(vehicleRepository.existsByLicensePlate(vehicleDto.getLicensePlate()))
			throw new ElementAlreadyExistsInDB(messageSource);
		return vehicleMapper.toDtoFromEntity(
				vehicleRepository.save(vehicleMapper.toEntityFromDtoWithoutId(vehicleDto)));
	}

	@Override
	public Collection<VehicleDto> getAllVehicle() {
		return vehicleRepository.findAll().stream()
				.map((vehicle) -> vehicleMapper.toDtoFromEntity(vehicle))
				.collect(Collectors.toList());
	}

	@Override
	public VehicleDto getVehicle(Long idVehicle) {
		return vehicleMapper.toDtoFromEntity(
				vehicleRepository.findById(idVehicle)
				.orElseThrow(() -> new ElementNotFoundDBException(messageSource)));
	}

	@Override
	public void deleteVehicle(Long idVehicle) {
		if(vehicleRepository.existsById(idVehicle)) {
			vehicleRepository.deleteById(idVehicle);
		}else {
			throw new ElementNotFoundDBException(messageSource);
		}
	}
	
}
